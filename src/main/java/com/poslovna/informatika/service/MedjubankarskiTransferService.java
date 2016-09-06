package com.poslovna.informatika.service;

import com.poslovna.informatika.entities.MedjubankarskiTransfer;
import com.poslovna.informatika.repository.MedjubankarskiTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("medjubankarskiTransferService")
public class MedjubankarskiTransferService {

    @Autowired
    MedjubankarskiTransferRepository medjubankarskiTransferRepository;

    public MedjubankarskiTransfer findOne(Integer id) {
        return medjubankarskiTransferRepository.findOne(id);
    }

    public List<MedjubankarskiTransfer> findAll() {
        return medjubankarskiTransferRepository.findAll();
    }

    public MedjubankarskiTransfer save(MedjubankarskiTransfer medjubankarskiTransfer) {
        return medjubankarskiTransferRepository.save(medjubankarskiTransfer);
    }

}
