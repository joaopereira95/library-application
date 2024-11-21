package br.com.elotech.oxy.library.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080/");
        devServer.setDescription("URL da aplicação no ambiente de desenvolvimento");

        Contact contact = new Contact();
        contact.setEmail("joao_eduardo_pereira@hotmail.com");
        contact.setName("João Eduardo Pereira");

        Info info = new Info()
                .title("Library Application")
                .version("1.0")
                .contact(contact)
                .description("Gestão de Biblioteca com Recomendação de Livros");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
