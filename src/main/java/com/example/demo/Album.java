package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "album_table")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String albumName;
    private long albumYear;
    private String albumDescription;
    private String albumCoverUrl;

    @OneToMany(mappedBy = "songAlbum",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<Song> songs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist albumOwner;

    public Album() {}

    public Album(String albumName, long albumYear, String albumDescription, Artist albumOwner) {
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.albumDescription = albumDescription;
        this.albumCoverUrl = albumCoverUrl;
        this.albumOwner = albumOwner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(long albumYear) {
        this.albumYear = albumYear;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    public Artist getAlbumOwner() {
        return albumOwner;
    }

    public void setAlbumOwner(Artist albumOwner) {
        this.albumOwner = albumOwner;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
