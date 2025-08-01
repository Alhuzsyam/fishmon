package com.fishmon.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fishmon.Model.Dao.Relay;
import com.fishmon.Model.dto.Response;



@RestController
@CrossOrigin(origins = "*") // cros origins
@RequestMapping("/api/control")
public class RelayController {

    @Autowired
    private com.fishmon.Services.RelayService relayService;

    @GetMapping("/relay/all")
    public Response<Object> getAllRelay(@RequestParam String id){
        Response<Object> res = new Response<>();

        try{
            List<Relay> relay = relayService.getAllRelaysById(id); 

            if(relay == null || relay.isEmpty()){

            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            res.setMessage("Relay Not Found");
            res.setPayload(null);
            }else{
            res.setStatus(HttpStatus.OK.toString());
            res.setMessage("Relay List");
            res.setPayload(relay);
            }

        }catch(Exception e){
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            res.setMessage(e.getMessage());
            res.setPayload(null);
        }
        return res;
    }

    @GetMapping("/micro/getByCode")
    public Response<Relay> getRelayByCode(@RequestParam String code, @RequestParam String iduser) {
        Response<Relay> res = new Response<>();

        try {
            Relay relay = relayService.getRelayByCode(code, iduser);

            if (relay != null) {
                res.setStatus(HttpStatus.OK.toString());
                res.setMessage("Relay found");
                res.setPayload(relay);
            } else {
                res.setStatus(HttpStatus.NOT_FOUND.toString());
                res.setMessage("Relay not found");
                res.setPayload(null);
            }
        } catch (Exception e) {
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            res.setMessage("Error: " + e.getMessage());
            res.setPayload(null);
        }

        return res;
    }
    @PostMapping("/save")
    public Response<Relay> saveRelay(@RequestBody Relay relay) {
        Response<Relay> res = new Response<>();

        try {
            Relay savedRelay = relayService.saveRelay(relay);
            res.setStatus(HttpStatus.CREATED.toString());
            res.setMessage("Relay saved successfully");
            res.setPayload(savedRelay);
        } catch (Exception e) {
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            res.setMessage("Error: " + e.getMessage());
            res.setPayload(null);
        }
        System.out.println();

        return res;
    }
    @PutMapping("/updateValByCode")
    public Response<Object> updateRelayVal(@RequestParam String code, @RequestParam Boolean val, @RequestParam String id) {
    Response<Object> res = new Response<>();
    Map<String, Object> value = new LinkedHashMap<>();

    try {
        boolean updated = relayService.updateRelayValByCode(code, val, id);
        if (updated) {
            res.setStatus(HttpStatus.OK.toString());
            res.setMessage("Relay value updated successfully");
             value.put("val", val ? "ON" : "OFF");
            res.setPayload(value);
        } else {
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            res.setMessage("Relay with specified code not found");
            res.setPayload(null);
        }
    } catch (Exception e) {
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        res.setMessage("Error: " + e.getMessage());
        res.setPayload(null);
    }

    return res;
}
}