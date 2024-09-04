package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Test
    @DisplayName("Cenario de pet disponível")
    void cenarioPetDisponivel() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Cenario de pet indisponivel")
    void cenarioPetIndisponivel() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L, 1L, "Novo mascote");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto), "Pet já foi adotado!");
    }

}