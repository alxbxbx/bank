package com.poslovna.informatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.repository.PravnoLiceRepository;

@Service("pravnoLiceService")
public class PravnoLiceService {

    @Autowired
    PravnoLiceRepository pravnoLiceRepository;

    public PravnoLice findOne(Integer id) {
        return pravnoLiceRepository.findOne(id);
    }

    public PravnoLice findByPib(String pib) {
        return pravnoLiceRepository.findByPib(pib);
    }

    public List<PravnoLice> findAll() {
        return pravnoLiceRepository.findAll();
    }

    public PravnoLice save(PravnoLice pravnoLice) {
        return pravnoLiceRepository.save(pravnoLice);
    }

    public void remove(Integer id) {
        pravnoLiceRepository.delete(id);
    }

}
