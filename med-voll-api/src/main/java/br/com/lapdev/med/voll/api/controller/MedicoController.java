package br.com.lapdev.med.voll.api.controller;

import br.com.lapdev.med.voll.api.medico.DadosCadastroMedico;
import br.com.lapdev.med.voll.api.medico.DadosListagemMedico;
import br.com.lapdev.med.voll.api.medico.Medico;
import br.com.lapdev.med.voll.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    // EX URL: .../medicos?size=10&page=1
    // ORDENACAO por URL: .../medicos?sort=crm,desc
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var medicos = repository.findAll(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(medicos);
    }
}
