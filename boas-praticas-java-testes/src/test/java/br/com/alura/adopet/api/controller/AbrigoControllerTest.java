package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AbrigoService;
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbrigoService service;

    @Autowired
    private JacksonTester<CadastroAbrigoDto> jsonDto;

    @Test
    @DisplayName("Cadastrar abrigo com erro de validação, esperado 400")
    void cadastrarAbrigoComErroDeValidacao() throws Exception {
        String json = "{}";

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastrar abrigo com email invalido, esperado 400")
    void cadastrarAbrigoComErroDeValidacaoComEmailInvalido() throws Exception {
        CadastroAbrigoDto dto = new CadastroAbrigoDto("teste", "41999999999", "emailinvalido");

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastrar abrigo com campos obrigatórios, esperado 200")
    void cadastrarAbrigoComSucesso() throws Exception {
        CadastroAbrigoDto dto = new CadastroAbrigoDto("teste", "41999999999", "teste@gmail.com");

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Abrigo cadatrado com sucesso!", response.getContentAsString());
    }
}
