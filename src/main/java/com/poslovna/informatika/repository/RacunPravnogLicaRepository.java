package com.poslovna.informatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.informatika.entities.RacunPravnogLica;

import java.util.List;

public interface RacunPravnogLicaRepository extends JpaRepository<RacunPravnogLica, Integer> {

    RacunPravnogLica findByBrojRacuna(String brojRacuna);

    List<RacunPravnogLica> findByVazeci(Boolean vazeci);

}
