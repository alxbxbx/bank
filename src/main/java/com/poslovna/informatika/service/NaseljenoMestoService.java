package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.NaseljenoMesto;
import com.poslovna.informatika.repository.NaseljenoMestoRepository;

@Service("naseljenoMestoService")
public class NaseljenoMestoService {

    @Autowired
    NaseljenoMestoRepository naseljenoMestoRepository;

    public NaseljenoMesto findOne(Integer id) {
        return naseljenoMestoRepository.findOne(id);
    }

    public List<NaseljenoMesto> findAll() {
        return naseljenoMestoRepository.findAll();
    }

    public NaseljenoMesto save(NaseljenoMesto naseljenoMesto) {
        return naseljenoMestoRepository.save(naseljenoMesto);
    }

    public List<NaseljenoMesto> findByDrzavaId(Integer drzavaId) {
        return naseljenoMestoRepository.findByDrzavaId(drzavaId);
    }

    public void remove(Integer id) {
        naseljenoMestoRepository.delete(id);
    }
}
