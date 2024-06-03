package com.android.giziku.models;

import java.io.Serializable;

public class ImunisasiModel implements Serializable {

    String namaImunisasi;
    String id;
    String deskripsiImunisasi;
    String img_url;

    public ImunisasiModel() {
    }

    public ImunisasiModel(String namaImunisasi, String id, String deskripsiImunisasi, String img_url) {
        this.namaImunisasi = namaImunisasi;
        this.id = id;
        this.deskripsiImunisasi = deskripsiImunisasi;
        this.img_url = img_url;
    }

    public String getNamaImunisasi() {
        return namaImunisasi;
    }

    public void setNamaImunisasi(String namaImunisasi) {
        this.namaImunisasi = namaImunisasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeskripsiImunisasi() {
        return deskripsiImunisasi;
    }

    public void setDeskripsiImunisasi(String deskripsiImunisasi) {
        this.deskripsiImunisasi = deskripsiImunisasi;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
