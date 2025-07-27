package com.fishmon.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fishmon.Model.Dao.TimerFeeders;
import com.fishmon.Repository.SchedulerRepos;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepos schedulerRepos;

    public TimerFeeders saveSchedule(TimerFeeders timer){
        TimerFeeders time = new TimerFeeders();
        time = schedulerRepos.save(timer);
        return time;
    }
}
