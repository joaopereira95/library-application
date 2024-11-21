package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.CriacaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class CriacaoUsuarioUseCaseImplTest {

    @Spy
    @InjectMocks
    private CriacaoUsuarioUseCaseImpl useCase;

    @Mock
    private CriacaoUsuarioOutboundPort outboundPort;

    @Test
    void deveRetornarErro_aoReceberDataCadastroMaiorQueDataAtual() {

        Usuario usuario = criarUsuario();
        usuario.setDataCadastro(LocalDate.now().plusDays(1));

        RegraNegocioException exception = Assertions.assertThrows(
                RegraNegocioException.class,
                () -> useCase.criar(usuario)
        );

        Assertions.assertTrue(exception.getMessage().contains("Data de cadastro não pode ser maior que a data atual."));

    }

    @Test
    void deveCriar_aoReceberParametrosCorretos() {

        Usuario usuario = criarUsuario();

        useCase.criar(usuario);

        Mockito.verify(outboundPort, Mockito.times(1)).criar(usuario);

    }


    private static Usuario criarUsuario() {
        return new Usuario(1);
    }
}
