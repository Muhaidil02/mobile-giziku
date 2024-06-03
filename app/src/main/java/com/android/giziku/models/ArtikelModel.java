package com.android.giziku.models;

import java.io.Serializable;

public class ArtikelModel implements Serializable {
    String judul;
    String keterangan;
    String img_url;
    String tanggal;
    String isi;

    public ArtikelModel() {
    }

    public ArtikelModel(String judul, String keterangan, String img_url, String tanggal, String isi) {
        this.judul = judul;
        this.keterangan = keterangan;
        this.img_url = img_url;
        this.tanggal = tanggal;
        this.isi = isi ;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
