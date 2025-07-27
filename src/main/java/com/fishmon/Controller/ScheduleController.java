package com.fishmon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fishmon.Model.Dao.TimerFeeders;
import com.fishmon.Model.dto.Response;
import com.fishmon.Services.SchedulerService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    
    @Autowired
    private SchedulerService service;

    
    @PostMapping("/save")
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
            res.setMessage("Error :" + e.getMessage());
            res.setPayload(null);
        }
        return res;

    }
    @GetMapping("/getTime")
    public Response<TimerFeeders> FindTime(@RequestParam String code, @RequestParam String id){
        Response<TimerFeeders> res = new Response<>();

       
        try{
            TimerFeeders time = service.GetSchedule(code, id);
            if(time !=null){
                res.setStatus(HttpStatus.OK.toString());
                res.setMessage("Success");
                res.setPayload(time);
            }else{   
                res.setStatus(HttpStatus.BAD_REQUEST.toString());
                res.setMessage("Timer Not Found");
                res.setPayload(null);
            }
        }catch(Exception e){
            res.setStatus(HttpStatus.BAD_GATEWAY.toString());
            res.setMessage("Error :"+ e.getMessage());
            res.setPayload(null);
        }
        
        return res;

    }
    
}
