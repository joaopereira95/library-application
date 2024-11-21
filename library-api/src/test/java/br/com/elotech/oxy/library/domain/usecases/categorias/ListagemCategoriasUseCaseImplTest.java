package br.com.elotech.oxy.library.domain.usecases.categorias;

import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListagemCategoriasUseCaseImplTest {

    @Spy
    @InjectMocks
    private ListagemCategoriasUseCaseImpl listagemCategoriasUseCase;

    @Mock
    private ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;

    @Test
    void deveListarCategorias_comSucesso() {
        listagemCategoriasUseCase.listar();
        Mockito.verify(consultaCategoriasOutboundPort, Mockito.times(1)).listar();
    }
}
