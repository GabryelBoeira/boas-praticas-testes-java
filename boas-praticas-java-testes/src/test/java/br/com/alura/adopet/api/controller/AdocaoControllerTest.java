package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
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
class AdocaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdocaoService service;

    @Test
    @DisplayName("Solicitar adocão com erro de validação, codigo esperado 400")
    void solicitarAdocaoComErroDeValidacao() throws Exception {
        String json = "{}";

        var response = mockMvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Solicitar adocão com campos nao preenchidos, codigo esperado 400")
    void solicitarAdocaoComErroDeValidacaoCamposNaoPreenchidos() throws Exception {
        String json = """
                {
                    "idTutor": 1,
                    "motivo": "novo pet"
                }
                """;

        var response = mockMvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Solicitar adocão com sucesso, codigo esperado 200")
    void solicitarAdocaoComSucesso() throws Exception {
        String json = """
                {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "novo pet"
                }
                """;

        var response = mockMvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

}
