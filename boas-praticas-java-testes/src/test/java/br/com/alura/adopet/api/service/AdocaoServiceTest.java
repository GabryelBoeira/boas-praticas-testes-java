package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoSolicitacaoAdocao validador1;

    @Mock
    private ValidacaoSolicitacaoAdocao validador2;

    @Mock
    private Abrigo abrigo;

    @Mock
    private Tutor tutor;

    @Mock
    private Pet pet;

    @InjectMocks
    private AdocaoService service;

    @Captor
    private ArgumentCaptor<Adocao> captorCaptor;

    private SolicitacaoAdocaoDto dto;


    @Test
    @DisplayName("Cenário para solicitar adocao com sucesso")
    void cenarioSolicitarAdocao01() {
        given(abrigo.getEmail()).willReturn("email@gmail.com");
        given(pet.getAbrigo()).willReturn(abrigo);
        when(petRepository.getReferenceById(pet.getId())).thenReturn(pet);
        when(tutorRepository.getReferenceById(tutor.getId())).thenReturn(tutor);
        this.dto = new SolicitacaoAdocaoDto(pet.getId(), tutor.getId(),"Novo mascote");

        service.solicitar(dto);

        //Verifica se a adocao foi salva/criada
        then(adocaoRepository).should().save(captorCaptor.capture());
        Adocao adocao = captorCaptor.getValue();
        assertEquals(pet, adocao.getPet());
        assertEquals(tutor, adocao.getTutor());
        assertEquals(dto.motivo(), adocao.getMotivo());

        //Verifica se o email foi enviado
        then(emailService).should().enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Cenário para solicitar adocao chama os validadores no processo de solicitacao")
    void cenarioSolicitarAdocao02() {
        this.dto = new SolicitacaoAdocaoDto(pet.getId(), tutor.getId(),"Novo mascote");
        given(abrigo.getEmail()).willReturn("email@gmail.com");
        given(pet.getAbrigo()).willReturn(abrigo);
        when(petRepository.getReferenceById(pet.getId())).thenReturn(pet);
        when(tutorRepository.getReferenceById(tutor.getId())).thenReturn(tutor);
        validacoes.add(validador1);
        validacoes.add(validador2);

        service.solicitar(dto);

        then(validador1).should().validar(dto);
        then(validador2).should().validar(dto);
    }

    @Test
    @DisplayName("sdfsdfsdf")
    void cenarioSolicitarAdocao03() {

    }

}