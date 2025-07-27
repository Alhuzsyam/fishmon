package com.fishmon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fishmon.Model.Dao.TimerFeeders;
import com.fishmon.Model.dto.Response;
import com.fishmon.Services.SchedulerService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    
    @Autowired
    private SchedulerService service;

    
    @GetMapping("/save")
    public Response<TimerFeeders> save(@RequestBody TimerFeeders time){
        Response<TimerFeeders> res = new Response<>();
        TimerFeeders setTime = new TimerFeeders();
        try{
            setTime = service.saveSchedule(time);
            res.setStatus(HttpStatus.CREATED.toString());
            res.setMessage("success");
            res.setPayload(setTime);
        }catch(Exception e){
            res.setStatus(HttpStatus.BAD_GATEWAY.toString());
            res.setMessage("Failed");
            res.setPayload(null);
        }
        return res;

    }  
    
}
