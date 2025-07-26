package com.fishmon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fishmon.Model.Dao.Relay;

import jakarta.transaction.Transactional;

@Repository
public interface RelayRepos extends JpaRepository<Relay,Long> {
     
    @Query("SELECT r FROM Relay r WHERE r.code = :code AND r.iduser = :iduser")
    Relay findByCodeandIdRelay(@Param("code") String code, @Param("iduser") String iduser);


     @Query("SELECT r FROM Relay r WHERE r.iduser = :iduser")
     List<Relay> findRelayByIduser(@Param("iduser") String iduser);

    @Modifying
    @Transactional
    @Query("UPDATE Relay r SET r.val = :val WHERE r.code = :code AND r.iduser = :iduser")
    int updateValByCode(@Param("code") String code, @Param("val") Boolean val, @Param("iduser") String iduser);
}
