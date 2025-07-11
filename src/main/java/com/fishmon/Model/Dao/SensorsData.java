package com.fishmon.Model.Dao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;


    @Column(name = "pv_voltage")
    private double pvVoltage;

    @Column(name = "pv_current")
    private double pvCurrent;

    @Column(name = "pv_power")
    private double pvPower;

    @Column(name = "batt_voltage")
    private double battVoltage;

    @Column(name = "batt_ch_current")
    private double battChCurrent;

    @Column(name = "batt_ch_power")
    private double battChPower;

    @Column(name = "load_current")
    private double loadCurrent;

    @Column(name = "load_power")
    private double loadPower;

    @Column(name = "batt_percentage")
    private double battPercentage;

    @Column(name = "batt_temp")
    private double battTemp;

    @Column(name = "batt_disch_current")
    private double battDischCurrent;

    @Column(name = "env_temp")
    private double envTemp;

    @Column(name = "ph_bioflok")
    private double phBioflok;

    @Column(name = "temp_bioflok")
    private double tempBioflok;

    @Column(name = "do_bioflok")
    private double doBioflok;

    @Column(name = "code")
    private String code;

    @Column(name = "iduser")
    private String iduser;
}
