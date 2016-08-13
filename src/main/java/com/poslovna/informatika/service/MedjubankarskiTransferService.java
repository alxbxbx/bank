package com.poslovna.informatika.service;

import com.poslovna.informatika.entities.MedjubankarskiTransfer;
import com.poslovna.informatika.repository.MedjubankarskiTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("medjubankarskiTransferService")
public class MedjubankarskiTransferService {

    @Autowired
    MedjubankarskiTransferRepository medjubankarskiTransferRepository;

    public MedjubankarskiTransfer findOne(Integer id) {
        return medjubankarskiTransferRepository.findOne(id);
    }

    public MedjubankarskiTransfer save(MedjubankarskiTransfer medjubankarskiTransfer) {
        return medjubankarskiTransferRepository.save(medjubankarskiTransfer);
    }

}
