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

    private String songDuration;

    private long songYear;

    private String songImageUrl;

//    private String sample;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Artist> artists;

    @OneToMany(mappedBy = "song",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.EAGER)
    private Set<Sale> sales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album songAlbum;


    public Song() {
    }

    public Song(String songTitle, String songGenre, String songDuration, long songYear, Album songAlbum) {
        this.songTitle = songTitle;
        this.songGenre = songGenre;
        this.songAlbum = songAlbum;
        this.songDuration = songDuration;
        this.songYear = songYear;
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


    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public long getSongYear() {
        return songYear;
    }

    public void setSongYear(long songYear) {
        this.songYear = songYear;
    }

    public String getSongImageUrl() {
        return songImageUrl;
    }

    public void setSongImageUrl(String songImageUrl) {
        this.songImageUrl = songImageUrl;
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

    public Album getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(Album songAlbum) {
        this.songAlbum = songAlbum;
    }
}
