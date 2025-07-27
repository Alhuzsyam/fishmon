package com.fishmon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fishmon.Model.Dao.TimerFeeders;

@Repository
public interface SchedulerRepos extends JpaRepository<TimerFeeders,Long> {

    @Query("SELECT r FROM TimerFeeders r WHERE r.code = :code AND r.iduser = :iduser")
    TimerFeeders findByCodeandIdSchFeeders(@Param("code") String code, @Param("iduser") String iduser);
    
}
