package br.com.lapdev.screenmatch.repository;

import br.com.lapdev.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
