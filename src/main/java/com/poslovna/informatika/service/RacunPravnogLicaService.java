package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.repository.RacunPravnogLicaRepository;

@Service
public class RacunPravnogLicaService {
	
	@Autowired
	RacunPravnogLicaRepository racunPravnogLicaRepository;
	
	public RacunPravnogLica findOne(Integer id){
		return racunPravnogLicaRepository.findOne(id);
	}
	
	public List<RacunPravnogLica> findAll(){
		return racunPravnogLicaRepository.findAll();
	}

	public RacunPravnogLica findByBrojRacuna(String brojRacuna) { return racunPravnogLicaRepository.findByBrojRacuna(brojRacuna); }

	public RacunPravnogLica save(RacunPravnogLica racunPravnogLica){
		return racunPravnogLicaRepository.save(racunPravnogLica);
	}
	
	public void remove(Integer id){
		racunPravnogLicaRepository.delete(id);
	}
}
