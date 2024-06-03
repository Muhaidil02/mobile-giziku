package com.android.giziku.models;

import java.io.Serializable;

public class VaksinModel implements Serializable {
    String tempatVaksin;
    String lokasiVaksin;
    String jarakLokasi;
    String ratingVaksin;
    String img_url;

    public String getimg_url() {
        return img_url;
    }

    public void setimg_url(String img_url) {
        this.img_url = img_url;
    }

    public VaksinModel(String tempatVaksin, String lokasiVaksin, String jarakLokasi, String ratingVaksin, String img_url) {
        this.tempatVaksin = tempatVaksin;
        this.lokasiVaksin = lokasiVaksin;
        this.jarakLokasi = jarakLokasi;
        this.ratingVaksin = ratingVaksin;
        this.img_url = img_url;
    }

    public VaksinModel() {
    }



    public String getTempatVaksin() {
        return tempatVaksin;
    }

    public void setTempatVaksin(String tempatVaksin) {
        this.tempatVaksin = tempatVaksin;
    }

    public String getLokasiVaksin() {
        return lokasiVaksin;
    }

    public void setLokasiVaksin(String lokasiVaksin) {
        this.lokasiVaksin = lokasiVaksin;
    }

    public String getJarakLokasi() {
        return jarakLokasi;
    }

    public void setJarakLokasi(String jarakLokasi) {
        this.jarakLokasi = jarakLokasi;
    }

    public String getRatingVaksin() {
        return ratingVaksin;
    }

    public void setRatingVaksin(String ratingVaksin) {
        this.ratingVaksin = ratingVaksin;
    }
}
