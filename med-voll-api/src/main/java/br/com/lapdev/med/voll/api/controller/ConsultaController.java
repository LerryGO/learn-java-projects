package br.com.lapdev.med.voll.api.controller;

import br.com.lapdev.med.voll.api.domain.consulta.AgendaDeColsultas;
import br.com.lapdev.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.lapdev.med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import br.com.lapdev.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeColsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
