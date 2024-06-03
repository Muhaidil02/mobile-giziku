package com.android.giziku.models;

import java.io.Serializable;

public class JenisVaksinModel implements Serializable {
    String nama;
    String harga;
    String deskripsi;
    String img_url;

    public JenisVaksinModel(String nama, String harga, String deskripsi, String img_url ) {
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public JenisVaksinModel() {
    }
}
