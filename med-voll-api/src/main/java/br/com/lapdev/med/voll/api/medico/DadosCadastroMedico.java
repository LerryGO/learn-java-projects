package br.com.lapdev.med.voll.api.medico;

import br.com.lapdev.med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
