package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.repository.RacunPravnogLicaRepository;

@Service("racunPravnogLicaService")
public class RacunPravnogLicaService {
	
	@Autowired
	RacunPravnogLicaRepository racunPravnogLicaRepository;
	
	public RacunPravnogLica findOne(Integer id){
		return racunPravnogLicaRepository.findOne(id);
	}
	
	public List<RacunPravnogLica> findAll(){
		return racunPravnogLicaRepository.findAll();
	}

	public RacunPravnogLica findByBrojRacuna(String brojRacuna) {
		RacunPravnogLica rpl = racunPravnogLicaRepository.findByBrojRacuna(brojRacuna);
		if(rpl == null)
			return null;
		if(rpl.isVazeci() == false)
			return null;
		else
			return rpl;
	}

	public RacunPravnogLica save(RacunPravnogLica racunPravnogLica){
		return racunPravnogLicaRepository.save(racunPravnogLica);
	}
	
	public void remove(Integer id){
		RacunPravnogLica rpl = racunPravnogLicaRepository.findOne(id);
		rpl.setVazeci(false);
		racunPravnogLicaRepository.save(rpl);
	}
}
