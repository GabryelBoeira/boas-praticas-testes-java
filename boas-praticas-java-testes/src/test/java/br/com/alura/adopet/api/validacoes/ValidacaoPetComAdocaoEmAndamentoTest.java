package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
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
class ValidacaoPetComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacao;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Cenario de pet com adocao em andamento")
    void cenarioPetComAdocaoEmAndamento() {
        when(adocaoRepository.existsByPetIdAndStatus(anyLong(), any())).thenReturn(true);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto), "Pet já está aguardando avaliação para ser adotado!");
    }

    @Test
    @DisplayName("Cenario de pet que não tenha adocao em andamento")
    void cenarioPetSemAdocaoEmAndamento() {
        when(adocaoRepository.existsByPetIdAndStatus(anyLong(), any())).thenReturn(false);

        assertDoesNotThrow(() -> validacao.validar(dto));
    }

}