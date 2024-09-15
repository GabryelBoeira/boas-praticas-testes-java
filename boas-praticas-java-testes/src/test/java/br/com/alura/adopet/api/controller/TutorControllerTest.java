package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TutorService service;

    @Autowired
    private JacksonTester<CadastroTutorDto> cadastrarDto;

    @Autowired
    private JacksonTester<AtualizacaoTutorDto> atualizarDto;

    @Test
    @DisplayName("Cadastrar tutor com erro de validação, esperado 400")
    void cadastrarTutorComErroDeValidacao() throws Exception {
        String json = "{}";

        var response = mockMvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastrar tutor com erro de validação do nome, esperado 400")
    void cadastrarTutorComErroDeValidacaoComNomeInvalido() throws Exception {
        CadastroTutorDto dto = new CadastroTutorDto("", "955", "teste@gmail.com");

        var response = mockMvc.perform(
                post("/tutores")
                        .content(cadastrarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastrar tutor com erro de validação do telefone, esperado 400")
    void cadastrarTutorComErroDeValidacaoComTelefoneInvalido() throws Exception {
        CadastroTutorDto dto = new CadastroTutorDto("teste", "955", "teste@gmail.com");

        var response = mockMvc.perform(
                post("/tutores")
                        .content(cadastrarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastrar tutor com erro de validaçãodo email, esperado 400")
    void cadastrarTutorComErroDeValidacaoComEmailInvalido() throws Exception {
        CadastroTutorDto dto = new CadastroTutorDto("teste", "41999999999", "emailinvalido");

        var response = mockMvc.perform(
                post("/tutores")
                        .content(cadastrarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Atualizar tutor com erro de validação, esperado 400")
    void atualizarTutorComErroDeValidacao() throws Exception {
        String json = "{}";

        var response = mockMvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Atualizar tutor com erro de validação do nome, esperado 400")
    void atualizarTutorComErroDeValidacaoComNomeInvalido() throws Exception {
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1L, "", "41999999999", "teste@gmail.com");

        var response = mockMvc.perform(
                put("/tutores")
                        .content(atualizarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Atualizar tutor com erro de validação do telefone, esperado 400")
    void atualizarTutorComErroDeValidacaoComTelefoneInvalido() throws Exception {
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1L, "teste", "955", "teste@gmail.com");

        var response = mockMvc.perform(
                put("/tutores")
                        .content(atualizarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Atualizar tutor com erro de validação do email, esperado 400")
    void atualizarTutorComErroDeValidacaoComEmailInvalido() throws Exception {
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1L, "teste", "41999999999", "emailinvalido");

        var response = mockMvc.perform(
                put("/tutores")
                        .content(atualizarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Atualizar tutor com campos obrigatórios, esperado 200")
    void atualizarTutorComSucesso() throws Exception {
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1L, "teste", "41999999999", "teste@gmail.com");

        var response = mockMvc.perform(
                put("/tutores")
                        .content(atualizarDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}