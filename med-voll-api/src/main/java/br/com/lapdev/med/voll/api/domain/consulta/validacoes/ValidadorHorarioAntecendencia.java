package br.com.lapdev.med.voll.api.domain.consulta.validacoes;

import br.com.lapdev.med.voll.api.domain.VerificationException;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecendencia implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new VerificationException("Consulta deve ser agendada com antecendencia mínima de 30 minutos");

        }
    }
}
