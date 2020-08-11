package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="artist_table")
public class Artist {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String artistName;

    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Song> songs;

    public Artist() {
    }

    public Artist(String artistName, Set<Song> songs) {
        this.artistName = artistName;
        this.songs = songs;
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

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

}
