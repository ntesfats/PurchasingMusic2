package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sale_table")
public class Sale {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private double totalPrice;

    private boolean isPurchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Song> songs;


    public Sale() {
    }

    public Sale(User user, double totalPrice, boolean isPurchase) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.isPurchase = isPurchase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(boolean purchase) {
        isPurchase = purchase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
