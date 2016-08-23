package com.poslovna.informatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.informatika.entities.NaseljenoMesto;

import java.util.List;

public interface NaseljenoMestoRepository extends JpaRepository<NaseljenoMesto, Integer>{

    List<NaseljenoMesto> findByDrzavaId(Integer drzavaId);

}
