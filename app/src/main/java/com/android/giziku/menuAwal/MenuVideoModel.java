package com.android.giziku.menuAwal;

public class MenuVideoModel {
    String judul;
    String thumbnail;
    String videoUrl;

    public MenuVideoModel() {
    }

    public MenuVideoModel(String judul, String thumbnail, String videoUrl) {
        this.judul = judul;
        this.thumbnail = thumbnail;
        this.videoUrl = videoUrl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
