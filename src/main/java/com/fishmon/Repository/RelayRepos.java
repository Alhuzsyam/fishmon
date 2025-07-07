package com.fishmon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fishmon.Model.Dao.Relay;

import jakarta.transaction.Transactional;

@Repository
public interface RelayRepos extends JpaRepository<Relay,Long> {
     Relay findByCode(String code);

    @Modifying
    @Transactional
    @Query("UPDATE Relay r SET r.val = :val WHERE r.code = :code")
    int updateValByCode(@Param("code") String code, @Param("val") Boolean val);
}
