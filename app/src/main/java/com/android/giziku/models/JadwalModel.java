package com.android.giziku.models;

import java.io.Serializable;

public class JadwalModel implements Serializable {

    String hari;
    String tanggal;
    String bulan;


    public JadwalModel(String hari, String tanggal, String bulan, long waktu) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.bulan = bulan;

    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }



    public JadwalModel() {
    }
}
