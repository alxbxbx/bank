package com.poslovna.informatika.repository;

import com.poslovna.informatika.entities.RacunPravnogLica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.informatika.entities.DnevnoStanjeRacuna;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;

public interface DnevnoStanjeRacunaRepository extends JpaRepository<DnevnoStanjeRacuna, Integer>{

    DnevnoStanjeRacuna findTop1ByRacunPravnogLicaIdOrderByDatumPrometaDesc(Integer racunPravnogLicaId);

}
