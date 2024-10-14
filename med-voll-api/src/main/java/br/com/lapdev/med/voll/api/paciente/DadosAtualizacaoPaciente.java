package br.com.lapdev.med.voll.api.paciente;

import br.com.lapdev.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {

    public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {
        public DadosListagemPaciente(Paciente paciente) {
            this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
        }
    }
}
