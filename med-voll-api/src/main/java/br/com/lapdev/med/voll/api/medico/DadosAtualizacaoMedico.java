package br.com.lapdev.med.voll.api.medico;

import br.com.lapdev.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
