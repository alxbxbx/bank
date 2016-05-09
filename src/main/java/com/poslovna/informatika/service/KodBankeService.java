package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.KodBanke;
import com.poslovna.informatika.repository.KodBankeRepository;

@Service
public class KodBankeService {
	
	@Autowired
	KodBankeRepository kodBankeRepository;
	
	public KodBanke findOne(Integer id){
		return kodBankeRepository.findOne(id);
	}
	
	public List<KodBanke> findAll(){
		return kodBankeRepository.findAll();
	}
	
	public KodBanke save(KodBanke kodBanke){
		return kodBankeRepository.save(kodBanke);
	}
	
	public void remove(Integer id){
		kodBankeRepository.delete(id);
	}
}
