package com.fishmon.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fishmon.Model.Dao.Kolam;
import com.fishmon.Repository.KolamRepos;

import jakarta.transaction.Transactional;

@Service
public class KolamService {
    @Autowired
    KolamRepos kolamRepos;

    public Kolam addKolam(Kolam kolam){
        return kolamRepos.save(kolam);
    }
    public Kolam selectKolambyId(String code, String id){
        return kolamRepos.findKolamBycodeandIduser(code,id);
    }

     public List<Kolam> selectallKolam(String id){
        return kolamRepos.findKolamByIduser(id);
    }

    @Transactional
    public boolean nonactivatekolam(String code, Boolean status, String id) {
        int updatedRows = kolamRepos.updateStatusByCode(code, status, id);
        return updatedRows > 0;
    }
    
}
