package com.fishmon.Repository;

import org.springframework.stereotype.Repository;

import com.fishmon.Model.Dao.Kolam;


import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface KolamRepos extends JpaRepository<Kolam,Long> {

     @Query("SELECT r FROM Kolam r WHERE r.code = :code AND r.iduser = :iduser")
     Kolam findKolamBycodeandIduser(@Param("code") String code, @Param("iduser") String iduser);

     @Query("SELECT r FROM Kolam r WHERE r.iduser = :iduser")
     List<Kolam> findKolamByIduser(@Param("iduser") String iduser);

    @Modifying
    @Transactional
    @Query("UPDATE Kolam k SET k.status = :status WHERE k.code = :code AND k.iduser = :iduser")
    int updateStatusByCode(@Param("code") String code, @Param("status") Boolean status, @Param("iduser") String iduser);
}
