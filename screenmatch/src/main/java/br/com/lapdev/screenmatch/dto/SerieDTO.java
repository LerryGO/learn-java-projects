package br.com.lapdev.screenmatch.dto;

import br.com.lapdev.screenmatch.model.Categoria;

public record SerieDTO(
        Long id, String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse) {

}
