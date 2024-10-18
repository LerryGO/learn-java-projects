package br.com.lapdev.med.voll.api.domain.consulta;

import br.com.lapdev.med.voll.api.domain.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @JsonAlias({"idMedico","id_medico","medico_id"})
        Long idMedico,

        @NotNull
        @JsonAlias({"idPaciente","id_paciente","paciente_id"})
        Long idPaciente,

        @NotNull
        @Future
        // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade
) {

}
