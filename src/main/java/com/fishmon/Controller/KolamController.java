package com.fishmon.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fishmon.Model.Dao.Kolam;
import com.fishmon.Model.dto.Response;
import com.fishmon.Services.KolamService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/kolam")
public class KolamController {

    @Autowired
    private KolamService kolamService;

    @PostMapping("/add")
    public Response<Object> Addkolam(@RequestBody Kolam kolam){

        Response<Object> res = new Response<>();
        try{
         Kolam addKolam = kolamService.addKolam(kolam);
         res.setStatus(HttpStatus.CREATED.toString());
         res.setMessage("Success");
         res.setPayload(addKolam);
        }
        catch(Exception e) {
        res.setStatus(HttpStatus.BAD_REQUEST.toString());
         res.setMessage("Error");
         res.setPayload(e.getCause());
        }
        return res;
    }

   @GetMapping("/select")
public Response<Object> getkolam(@RequestParam String code, @RequestParam String id){
    Response<Object> res = new Response<>();

    try {
        Kolam getkolam = kolamService.selectKolambyId(code, id);

        if (getkolam == null) {
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            res.setMessage("Kolam not found");
            res.setPayload(null);
        } else {
            res.setStatus(HttpStatus.OK.toString());
            res.setMessage("List Kolam");
            res.setPayload(getkolam);
        }

    } catch (Exception e) {
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        res.setMessage("Error: " + e.getMessage());
        res.setPayload(null);
    }

    return res;
}


   @GetMapping("/select/all")
public Response<Object> getAllkolam(@RequestParam String id){
    Response<Object> res = new Response<>();

    try {
        List<Kolam> getkolam = kolamService.selectallKolam(id);

        if (getkolam == null || getkolam.isEmpty()) {
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            res.setMessage("Tidak ada kolam ditemukan");
            res.setPayload(null);
        } else {
            res.setStatus(HttpStatus.OK.toString());
            res.setMessage("List Kolam");
            res.setPayload(getkolam);
        }

    } catch (Exception e) {
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        res.setMessage("Error: " + e.getMessage());
        res.setPayload(null);
    }

    return res;
}


    @PutMapping("/updatestatus")
    public Response<Object> updatestatus(@RequestParam String code,@RequestParam Boolean val, @RequestParam String id){
        Response<Object> res = new Response<>();
        Map<String, Object> value = new LinkedHashMap<>();
        try{
            boolean update = kolamService.nonactivatekolam(code,val,id);
            res.setStatus(HttpStatus.OK.toString());
            res.setMessage("success");
            value.put("val", val ? "Activate" : "Deactivate");
            res.setPayload(value);
        }catch(Exception e){
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            res.setMessage("Error");
            res.setPayload(e.getCause());
        }
        return res;
    }

}
