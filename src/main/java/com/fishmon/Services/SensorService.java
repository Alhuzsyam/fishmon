package com.fishmon.Services;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fishmon.Model.Dao.SensorsData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final JdbcTemplate jdbcTemplate;  // ❗️ TANPA @Autowired

    public void saveSensorData(SensorsData data) {
        String sql = """
            INSERT INTO sensor_data (
                `timestamp`, `pv_voltage`, `pv_current`, `pv_power`,
                `batt_voltage`, `batt_ch_current`, `batt_ch_power`,
                `load_current`, `load_power`, `batt_percentage`, `batt_temp`,
                `batt_disch_current`, `env_temp`, `ph_bioflok`,
                `temp_bioflok`, `do_bioflok`, `code`, `iduser`
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        // 18 parameter
        jdbcTemplate.update(sql,
            data.getTimestamp(),
            data.getPvVoltage(),
            data.getPvCurrent(),
            data.getPvPower(),
            data.getBattVoltage(),
            data.getBattChCurrent(),
            data.getBattChPower(),
            data.getLoadCurrent(),
            data.getLoadPower(),
            data.getBattPercentage(),
            data.getBattTemp(),
            data.getBattDischCurrent(),
            data.getEnvTemp(),
            data.getPhBioflok(),
            data.getTempBioflok(),
            data.getDoBioflok(),
            data.getCode(),
            data.getIduser()
        );
    }

        // Get all sensor data
        public List<SensorsData> getAllSensorData(String code, String iduser) {
        String sql = "SELECT * FROM sensor_data WHERE code = ? AND iduser = ?";
        List<SensorsData> data = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(SensorsData.class),code,iduser);
        return data;
    }

    public List<SensorsData> getlatestdata(String code, String iduser){
        String sql = "SELECT * FROM sensor_data WHERE code = ? AND iduser = ? Order by timestamp DESC LIMIT 1";
        List<SensorsData> data = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(SensorsData.class),code,iduser);
        return data;
    }

}
