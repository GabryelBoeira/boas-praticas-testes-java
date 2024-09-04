package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    private static Tutor TUTOR = new Tutor(1L, "Teste", "12345678901", "teste@teste.com");

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validacao;

    @Test
    @DisplayName("Cenario de tutor não tenha realizado 5 adocoes")
    void cenarioTutorComAdocaoMenosDeCincoAdocoes() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        when(adocaoRepository.countByTutorIdAndStatus(anyLong(), any())).thenReturn(3);

        assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Cenario de tutor tenha realizado 5 adocoes")
    void cenarioTutorComAdocaoComCincoAdocoes() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        when(adocaoRepository.countByTutorIdAndStatus(anyLong(), any())).thenReturn(5);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto), "Tutor chegou ao limite máximo de 5 adoções!");
    }

    @Test
    @DisplayName("Cenario de tutor tenha realizado mais de 5 adocoes")
    void cenarioTutorComAdocaoMaisDeCincoAdocoes() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        when(adocaoRepository.countByTutorIdAndStatus(anyLong(), any())).thenReturn(8);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto), "Tutor chegou ao limite máximo de 5 adoções!");
    }

}