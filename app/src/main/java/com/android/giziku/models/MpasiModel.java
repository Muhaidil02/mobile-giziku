package com.android.giziku.models;

import java.io.Serializable;

public class MpasiModel implements Serializable {
    String judul;
    String keterangan;
    String img_url;
    String tanggal;
    String kategori, funfact, waktu_memasak, bahan, usia, porsi, cara_membuat;

    public MpasiModel(String judul, String keterangan, String img_url, String tanggal, String kategori, String funfact, String waktu_memasak, String bahan, String usia, String porsi, String cara_membuat) {
        this.judul = judul;
        this.keterangan = keterangan;
        this.img_url = img_url;
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.funfact = funfact;
        this.waktu_memasak = waktu_memasak;
        this.bahan = bahan;
        this.usia = usia;
        this.porsi = porsi;
        this.cara_membuat = cara_membuat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFunfact() {
        return funfact;
    }

    public void setFunfact(String funfact) {
        this.funfact = funfact;
    }

    public String getWaktu_memasak() {
        return waktu_memasak;
    }

    public void setWaktu_memasak(String waktu_memasak) {
        this.waktu_memasak = waktu_memasak;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getPorsi() {
        return porsi;
    }

    public void setPorsi(String porsi) {
        this.porsi = porsi;
    }

    public String getCara_membuat() {
        return cara_membuat;
    }

    public void setCara_membuat(String cara_membuat) {
        this.cara_membuat = cara_membuat;
    }

    public MpasiModel() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
}
