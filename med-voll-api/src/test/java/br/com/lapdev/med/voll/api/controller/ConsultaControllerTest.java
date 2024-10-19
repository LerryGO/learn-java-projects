package br.com.lapdev.med.voll.api.controller;

import br.com.lapdev.med.voll.api.domain.consulta.AgendaDeColsultas;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.lapdev.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import br.com.lapdev.med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest  // Indica que este é um teste de integração para a aplicação Spring Boot.
@AutoConfigureMockMvc  // Configura o MockMvc para simular requisições HTTP aos endpoints.
@AutoConfigureJsonTesters  // Configura ferramentas para manipular e verificar JSON nos testes.
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;  // Simula chamadas HTTP nos testes.

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;  // Facilita conversão entre JSON e objetos.

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;  // Facilita verificação de respostas JSON.

    @MockBean
    private AgendaDeColsultas agendaDeColsultas;  // Simula o serviço de agendamento de consultas.

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser  // Simula um usuário autenticado.
    void agendar_cenario1() throws Exception {
        // Simula uma requisição POST sem dados válidos e verifica se retorna 400 (Bad Request).
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        // Cria dados válidos para o agendamento.
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        // Simula o comportamento do serviço ao agendar uma consulta.
        when(agendaDeColsultas.agendar(any())).thenReturn(dadosDetalhamento);

        // Envia a requisição com dados válidos e verifica se retorna 200 (OK).
        var response = mvc.perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(2L, 5L, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        // Verifica se o conteúdo da resposta é o esperado.
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
