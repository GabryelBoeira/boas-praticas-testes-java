package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AdocaoService;
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
class AdocaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdocaoService service;

    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> jsonDto;

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
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "");

        var response = mockMvc.perform(
                post("/adocoes")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Solicitar adocão com sucesso, codigo esperado 200")
    void solicitarAdocaoComSucesso() throws Exception {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "Motivo qualquer");

        var response = mockMvc.perform(
                post("/adocoes")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Adoção solicitada com sucesso!", response.getContentAsString());
    }

}
