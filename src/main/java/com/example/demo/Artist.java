package com.example.demo;

import javax.persistence.*;

@Entity
//@Table(name="Artist")
public class Artist {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String artistName;


    @ManyToOne (fetch = FetchType.EAGER)
    private Song song;

    public Artist() {
    }

    public Artist(String artistName, Song song) {
        this.artistName = artistName;
        this.song = song;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

}
