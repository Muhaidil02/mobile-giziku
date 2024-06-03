package com.android.giziku.models;

public class AnakModel {
    String nama;
     String tanggalLahir;
     String beratBadan;
     String tinggiBadan;
     String lingkarKepala;
     String jenisKelamin;
     boolean memilikiAlergi;

    public AnakModel() {
    }

    public AnakModel(String nama, String tanggalLahir, String beratBadan, String tinggiBadan, String lingkarKepala, String jenisKelamin, boolean memilikiAlergi) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.lingkarKepala = lingkarKepala;
        this.jenisKelamin = jenisKelamin;
        this.memilikiAlergi = memilikiAlergi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(String tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public String getLingkarKepala() {
        return lingkarKepala;
    }

    public void setLingkarKepala(String lingkarKepala) {
        this.lingkarKepala = lingkarKepala;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public boolean isMemilikiAlergi() {
        return memilikiAlergi;
    }

    public void setMemilikiAlergi(boolean memilikiAlergi) {
        this.memilikiAlergi = memilikiAlergi;
    }
}
