package br.com.lapdev.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.lapdev.med.voll.api.domain.VerificationException;
import br.com.lapdev.med.voll.api.domain.consulta.ConsultaRepository;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new VerificationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
