package br.com.lapdev.TabelaFipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {
}
