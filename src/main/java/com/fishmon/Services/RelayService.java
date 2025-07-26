package com.fishmon.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fishmon.Model.Dao.Relay;
import com.fishmon.Repository.RelayRepos;

import jakarta.transaction.Transactional;

@Service
public class RelayService {
     @Autowired
    private RelayRepos relayRepository;

    public Relay getRelayByCode(String code, String iduser) {
        return relayRepository.findByCodeandIdRelay(code, iduser);
    }
    public Relay saveRelay(Relay relay) {
        return relayRepository.save(relay);
    }

    public List<Relay> getAllRelaysById(String id){
        return relayRepository.findRelayByIduser(id);
        //findRelayByIduser
    }

    @Transactional
    public boolean updateRelayValByCode(String code, Boolean val, String id) {
        int updatedRows = relayRepository.updateValByCode(code, val, id);
        return updatedRows > 0;
    }
}
