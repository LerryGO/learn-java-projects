package br.com.lapdev.med.voll.api.domain.consulta.validacoes.agendamento;

import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
