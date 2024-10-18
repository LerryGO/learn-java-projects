package br.com.lapdev.med.voll.api.domain.consulta.validacoes;

import br.com.lapdev.med.voll.api.domain.VerificationException;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.lapdev.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new VerificationException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
