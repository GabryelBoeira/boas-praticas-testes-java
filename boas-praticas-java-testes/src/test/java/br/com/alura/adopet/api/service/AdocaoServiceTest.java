package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private List<ValidacaoSolicitacaoAdocao> validacoes;

    @Mock
    private Abrigo abrigo;

    @Mock
    private Tutor tutor;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @InjectMocks
    private AdocaoService service;

    @Test
    @DisplayName("Cenário para solicitar adocao com sucesso")
    void cenarioSolicitarAdocao01() {
        given(abrigo.getEmail()).willReturn("email@br.com");
        given(pet.getAbrigo()).willReturn(abrigo);
        when(petRepository.getReferenceById(any())).thenReturn(pet);

        service.solicitar(dto);

        then(adocaoRepository).should().save(any());
        then(emailService).should().enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Cenário para solicitar adocao com falha no processo de validação")
    void cenarioSolicitarAdocao02() {
        assertThrows(ValidacaoException.class, () -> service.solicitar(dto));

    }

    @Test
    @DisplayName("")
    void cenarioSolicitarAdocao03() {

    }

}