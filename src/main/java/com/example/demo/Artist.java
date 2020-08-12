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

    private String description;

    private String headShotUrl;

    @ManyToMany (mappedBy = "artists",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<Song> songs;

    @OneToMany(mappedBy = "albumOwner",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<Album> albums;

    public Artist() {
    }

    public Artist(String artistName, String description) {
        this.artistName = artistName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadShotUrl() {
        return headShotUrl;
    }

    public void setHeadShotUrl(String headShotUrl) {
        this.headShotUrl = headShotUrl;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
