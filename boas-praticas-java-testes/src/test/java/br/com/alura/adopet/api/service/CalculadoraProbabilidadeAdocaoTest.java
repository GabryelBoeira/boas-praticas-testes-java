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

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraProbabilidadeAdocao();
    }


    @Test
    @DisplayName("Cenario de gato com alta probabilidade")
    void cenarioGato01ProbabilidadeAlta() {
        // Cenario: Gato com peso baixo e idade alta
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 3, "Cinza", 4.0f), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de gato com media probabilidade")
    void cenarioGato02ProbabilidadeMedia() {
        // Cenario: Gato com peso baixo e idade alta
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 7, "Cinza", 4.0f), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de gato com baixa probabilidade")
    void cenarioGato03ProbabilidadeBaixa() {
        // Cenario: Gato com peso alto e idade alta
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 10, "Cinza", 7.0f), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com alta probabilidade")
    void cenarioCachorro01ProbabilidadeAlta() {
        // Cenario: Cachorro com peso alto e idade baixa
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 1, "Branco", 18.0F), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com media probabilidade")
    void cenarioCachorro02ProbabilidadeMedia() {
        // Cenario: Cachorro com peso baixo e idade media
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 5, "Branco", 10.0F), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Cenario de cachorro com baixa probabilidade")
    void cenarioCachorro03ProbabilidadeBaixa() {
        // Cenario: Cachorro com peso alto e idade alta
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Rex", "Dalmata", 9, "Branco", 18.0F), abrigo);

        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }


}
