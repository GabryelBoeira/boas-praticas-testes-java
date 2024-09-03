package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {

    private CalculadoraProbabilidadeAdocao calculadora;
    private static Abrigo ABRIGO = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraProbabilidadeAdocao();
    }

    @Test
    @DisplayName("Cenario de gato com alta probabilidade de adocão")
    void cenarioProbabilidadeAlta01() {
        // Cenario: Gato com peso baixo e idade alta
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 3, "Cinza", 4.0f), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com alta probabilidade de adocão")
    void cenarioProbabilidadeAlta02() {
        // Cenario: Cachorro com peso alto e idade baixa
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 1, "Branco", 18.0F), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de gato com media probabilidade de adocão")
    void cenarioGatoProbabilidadeMedia01() {
        // Cenario: Gato com peso baixo e idade alta
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 7, "Cinza", 4.0f), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com media probabilidade de adocão")
    void cenarioProbabilidadeMedia02() {
        // Cenario: Cachorro com peso baixo e idade media
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 5, "Branco", 10.0F), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de gato com baixa probabilidade de adocão")
    void cenarioProbabilidadeBaixa01() {
        // Cenario: Gato com peso alto e idade alta
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 10, "Cinza", 11.0f), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com baixa probabilidade de adocão")
    void cenarioProbabilidadeBaixa03() {
        // Cenario: Cachorro com peso alto e idade alta
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 12, "Branco", 18.0F), ABRIGO);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }

}
