package com.android.giziku.models;

import java.io.Serializable;

public class PembayaranVaksiin_Model implements Serializable {
    String nama_bank;
    String img_url;

    public String getNama_bank() {
        return nama_bank;
    }

    public void setNama_bank(String nama_bank) {
        this.nama_bank = nama_bank;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public PembayaranVaksiin_Model(String nama_bank, String img_url) {
        this.nama_bank = nama_bank;
        this.img_url = img_url;
    }

    public PembayaranVaksiin_Model() {
    }
}
