package br.com.lapdev.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.lapdev.med.voll.api.domain.VerificationException;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.lapdev.med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        // Escolha do médico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new VerificationException("Consulta não pode ser agendada com médico excluido");
        }
    }
}
