package br.com.lapdev.screenmatch_frases.controllers;

import br.com.lapdev.screenmatch_frases.dto.FraseDTO;
import br.com.lapdev.screenmatch_frases.services.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraseController {

    @Autowired
    private FraseService servico;

    @GetMapping("/series/frases")
    public FraseDTO obterFraseAleatoria(){
        return servico.obterFraseAleatoria();
    }
}
