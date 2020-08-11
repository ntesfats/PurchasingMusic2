package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="song_table")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String songTitle;

    private String songGenre;

    private String songAlbum;

    private String songDuration;

    @ManyToMany (mappedBy = "songs",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.EAGER)
    private Set<Artist> artists;

    @OneToMany(mappedBy = "song",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.EAGER)
    private Set<Sale> sales;

    public Song() {
    }

    public Song(String songTitle, String songGenre, String songAlbum, String songDuration) {
        this.songTitle = songTitle;
        this.songGenre = songGenre;
        this.songAlbum = songAlbum;
        this.songDuration = songDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

}
