package br.com.lapdev.screenmatch_frases.services;

import br.com.lapdev.screenmatch_frases.dto.FraseDTO;
import br.com.lapdev.screenmatch_frases.models.Frase;
import br.com.lapdev.screenmatch_frases.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {

    @Autowired
    private FraseRepository repositorio;

    public FraseDTO obterFraseAleatoria() {
        Frase frase = repositorio.buscaFraseAleatoria();
        return new FraseDTO(frase.getTitulo(), frase.getFrase(),frase.getPersonagem(),frase.getPoster());
    }
}
