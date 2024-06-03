package com.android.giziku.models;

import java.io.Serializable;

public class KonsultasiModel implements Serializable {
    String nama;
    String pengalaman;
    String jarak;
    String harga;
    String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public KonsultasiModel(String img_url) {
        this.img_url = img_url;
    }

    public KonsultasiModel() {
    }

    public KonsultasiModel(String nama, String pengalaman, String jarak, String harga) {
        this.nama = nama;
        this.pengalaman = pengalaman;
        this.jarak = jarak;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPengalaman() {
        return pengalaman;
    }

    public void setPengalaman(String pengalaman) {
        this.pengalaman = pengalaman;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
