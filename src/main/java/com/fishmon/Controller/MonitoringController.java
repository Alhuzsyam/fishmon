package com.fishmon.Controller;

import org.springframework.web.bind.annotation.*;
import com.fishmon.Model.Dao.SensorsData;
import com.fishmon.Model.dto.Response;
import com.fishmon.Services.SensorService;

import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final SensorService sensorService; // ✅ FINAL agar di-inject otomatis oleh Lombok

    @GetMapping("/welcome")
    public String welcome() {
        return "Fishmon";
    }

    // get all data by code
    @GetMapping("/sensors")
    public Response<Object> getAlldata(@RequestParam String code, @RequestParam String id) {
        Response<Object> res = new Response<>();
        List<SensorsData> alldata = sensorService.getAllSensorData(code,id);
        System.out.println("datas = "+alldata);
        if(alldata.isEmpty()){
            res.setMessage("data is not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            res.setPayload(null);
        }else{
             res.setMessage("Data sensors");
             res.setStatus(HttpStatus.OK.toString());
             res.setPayload(alldata);
        }
        return res;
    }
    // get lastes data
    @GetMapping("/sensors/latest")
    public Response<Object> getlatestdata(@RequestParam String code, @RequestParam String id) {
        Response<Object> res = new Response<>();
        List<SensorsData> alldata = sensorService.getlatestdata(code,id);
        System.out.println("datas = "+alldata);
        if(alldata.isEmpty()){
            res.setMessage("data is not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            res.setPayload(null);
        }else{
             res.setMessage("Data sensors");
             res.setStatus(HttpStatus.OK.toString());
             res.setPayload(alldata);
        }
        return res;
    }
    

    @PostMapping("/micro/sensors")
    public ResponseEntity<Response<Map<String, Object>>> saveSensorData(@RequestBody SensorsData data) {
    try {
        sensorService.saveSensorData(data);

        // Konversi objek ke Map, tanpa field ID
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("timestamp", data.getTimestamp());
        json.put("pvVoltage", data.getPvVoltage());
        json.put("pvCurrent", data.getPvCurrent());
        json.put("pvPower", data.getPvPower());
        json.put("battVoltage", data.getBattVoltage());
        json.put("battChCurrent", data.getBattChCurrent());
        json.put("battChPower", data.getBattChPower());
        json.put("loadCurrent", data.getLoadCurrent());
        json.put("loadPower", data.getLoadPower());
        json.put("battPercentage", data.getBattPercentage());
        json.put("battTemp", data.getBattTemp());
        json.put("battDischCurrent", data.getBattDischCurrent());
        json.put("envTemp", data.getEnvTemp());
        json.put("phBioflok", data.getPhBioflok());
        json.put("tempBioflok", data.getTempBioflok());
        json.put("doBioflok", data.getDoBioflok());
        json.put("code", data.getCode());
        json.put("iduser", data.getIduser());

        Response<Map<String, Object>> response = new Response<>("success", "Data berhasil disimpan", json);
        return ResponseEntity.ok(response);

    } catch (Exception e) {
        Response<Map<String, Object>> response = new Response<>("error", "Gagal menyimpan data: " + e.getMessage(), null);
        return ResponseEntity.internalServerError().body(response);
    }
}

}
