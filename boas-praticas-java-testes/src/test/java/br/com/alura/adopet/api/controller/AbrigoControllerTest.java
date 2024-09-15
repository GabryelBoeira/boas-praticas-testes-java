package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbrigoService service;

    @Test
    @DisplayName("Solicitar adocão com erro de validação, codigo esperado 400")
    void cadastrarAbrigoComErroDeValidacao() throws Exception {
        String json = "{}";

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Solicitar adocão com erro de validação no email, codigo esperado 400")
    void cadastrarAbrigoComErroDeValidacaoComEmailInvalido() throws Exception {
        String json = """
                {"nome":"teste","telefone":"41999999999","email":"emailinvalido"}""";

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Solicitar adocão com sucesso, codigo esperado 200")
    void cadastrarAbrigoComSucesso() throws Exception {
        String json = """
                {"nome":"teste","telefone":"41999999999","email":"teste@gmail.com"}""";

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }
}
