package br.com.elotech.oxy.library.infrastructure.adapters.outbound.http.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.http.livros.dtos.BooksResponse;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.http.livros.dtos.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class ConsultaLivrosOnlineRest implements ConsultaLivrosOnlineOutboundPort {

    private static final String URL_BOOKS = "https://www.googleapis.com/books/v1/";
    private static final String URL_BOOKS_LIST = URL_BOOKS + "volumes?q=intitle:%s&maxResults=40&fields=items(id,volumeInfo(title,authors,publishedDate,categories,industryIdentifiers))";
    private static final String URL_BOOK = URL_BOOKS + "volumes/%s";

    private static final String ISBN_10 = "ISBN_10";
    private static final String ISBN_13 = "ISBN_13";

    private final RestClient restClient;

    public ConsultaLivrosOnlineRest() {
        restClient = RestClient.create();
    }

    @Override
    public List<LivroOnline> consultarPorTitulo(String titulo) {

        BooksResponse result;

        try {
            result = restClient.get()
                    .uri(URL_BOOKS_LIST.formatted(titulo))
                    .retrieve()
                    .body(BooksResponse.class);

        } catch (HttpClientErrorException.NotFound ex) {
            return List.of();
        }

        if (result == null || result.getItems() == null) {
            return List.of();
        }

        return result.getItems().stream()

                .filter(item ->
                                    item.getVolumeInfo().getPublishedDate() != null
                                && item.getVolumeInfo().getIndustryIdentifiers() != null
                                && item.getVolumeInfo().getAuthors() != null
                                && item.getVolumeInfo().getCategories() != null)

                .filter(item -> item.getVolumeInfo().getIndustryIdentifiers()
                        .stream()
                        .anyMatch(id -> ISBN_10.equals(id.getType()) || ISBN_13.equals(id.getType())))

                .map(item ->
                        new LivroOnline(
                                item.getId(),
                                item.getVolumeInfo().getTitle(),
                                String.join(" / ", item.getVolumeInfo().getAuthors()),
                                item.getVolumeInfo().getIndustryIdentifiers().getFirst().getIdentifier(),
                                formatarParaData(item.getVolumeInfo().getPublishedDate()),
                                new Categoria(String.join(" / ", item.getVolumeInfo().getCategories())))
                ).toList();

    }

    @Override
    public Optional<LivroOnline> consultarPorId(String id) {

        Item item;

        try {
            item = restClient.get()
                    .uri(URL_BOOK.formatted(id))
                    .retrieve()
                    .body(Item.class);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new RecursoNaoEncontradoException(id, LivroOnline.class);
        }

        if (item == null) {
            throw new RecursoNaoEncontradoException(id, LivroOnline.class);
        }

        return Optional.of(new LivroOnline(
                item.getId(),
                item.getVolumeInfo().getTitle(),
                String.join(" / ", item.getVolumeInfo().getAuthors()),
                item.getVolumeInfo().getIndustryIdentifiers().getFirst().getIdentifier(),
                formatarParaData(item.getVolumeInfo().getPublishedDate()),
                new Categoria(String.join(" / ", item.getVolumeInfo().getCategories()))));

    }

    private static LocalDate formatarParaData(String data) {
        if (data == null) {
            return null;
        }

        if (data.length() == 4) { // apenas ano
            return LocalDate.of(Integer.parseInt(data), Month.JANUARY, 1);
        }

        if (data.length() == 7) { // apenas ano e mes
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return LocalDate.parse(data + "-01", formatter);
        }

        if (data.length() == 10) { // data
            return LocalDate.parse(data);
        }

        // data e hora
        return LocalDateTime.parse(data).toLocalDate();

    }

}
