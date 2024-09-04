package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

    private static Tutor TUTOR = new Tutor(1L, "Teste", "12345678901", "teste@teste.com");

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private Pet pet;

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacao;

    @Test
    @DisplayName("Cenario de tutor que não tenha adocao em andamento")
    void cenarioTutorSemAdocaoEmAndamento() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        when(tutorRepository.getReferenceById(anyLong())).thenReturn(TUTOR);
        when(adocaoRepository.findAll()).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Cenario de tutor que tenha adocao em andamento")
    void cenarioTutorAdocaoEmAndamento() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        when(tutorRepository.getReferenceById(anyLong())).thenReturn(TUTOR);
        when(adocaoRepository.findAll()).thenReturn(List.of(new Adocao(TUTOR, pet, "Novo mascote")));

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

}