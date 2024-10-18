package br.com.lapdev.med.voll.api.domain.consulta.validacoes;

import br.com.lapdev.med.voll.api.domain.VerificationException;
import br.com.lapdev.med.voll.api.domain.consulta.ConsultaRepository;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsuktaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsuktaNoMesmoHorario) {
            throw new VerificationException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
