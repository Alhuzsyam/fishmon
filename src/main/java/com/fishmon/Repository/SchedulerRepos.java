package com.fishmon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fishmon.Model.Dao.TimerFeeders;

@Repository
public interface SchedulerRepos extends JpaRepository<TimerFeeders,Long> {
    
}
