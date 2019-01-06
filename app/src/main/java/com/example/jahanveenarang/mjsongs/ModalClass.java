package com.example.jahanveenarang.mjsongs;

import com.google.gson.annotations.SerializedName;

public class ModalClass {




    @SerializedName("artworkUrl30")
    private String image;

    @SerializedName("artistName")
    private String artist;

    @SerializedName("trackName")
    private String nameOfSong;

    @SerializedName("collectionName")
    private String album;

    @SerializedName("previewUrl")
    private String song;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        this.nameOfSong = nameOfSong;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }


}
