package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaleRepository saleRepository;

    public void run(String... args){

//  Admin
    User admin = new User("admin", "admin@gmail.com","admin", "admin", "admin", true);

    //  User or Customer that have ability to purchase a music
    User userOne = new User("user", "user@gmail.com", "password", "User", "One", true);
    User userTwo = new User("user2", "user2@gmail.com", "password", "User2", "Two", true);

    Role adminRole = new Role("admin", "ROLE_ADMIN");
    Role userRole = new Role("user", "ROLE_USER");
    Role userRole2 = new Role("user2", "ROLE_USER");



    userRepository.save(admin);
    roleRepository.save(adminRole);

    userRepository.save(userOne);
    userRepository.save(userTwo);

    roleRepository.save(userRole);
    roleRepository.save(userRole2);

//  Creating Songs
    Song songOne = new Song("songTitle", "songGenre", "songAlbum", "2:00");
    Song songTwo = new Song("songTitle2", "songGenre2", "songAlbum2", "4:00");
    songRepository.save(songOne);
    songRepository.save(songTwo);

//  Creating Set of Songs
    Set<Song> songSet = new HashSet<>();
    songSet.add(songOne);
    songSet.add(songTwo);

//  Creating Artist and setting up all the song they have created by the artist.
    Artist artistOne = new Artist("artistOneName", songSet);

//  Saving Artist
    artistRepository.save(artistOne);

//    Creating Sale and setting the Song that are purchased
    Sale sale1 = new Sale(songOne,5.0);
    Sale sale2 = new Sale(songTwo,2.0);

//    Saving Sole1 and Sale2
    saleRepository.save(sale1);
    saleRepository.save(sale2);

//    Users that purchase songOne
    Set<User> userThatBoughtSongOne = new HashSet<>();
    userThatBoughtSongOne.add(userOne);

//  Users that purchase songTwo
    Set<User> userThatBoughtSongTwo = new HashSet<>();
    userThatBoughtSongTwo.add(userOne);
    userThatBoughtSongTwo.add(userTwo);


//    Setting users to Sale1
    sale1.setUsers(userThatBoughtSongOne);

//    Setting users to Sale2
    sale2.setUsers(userThatBoughtSongTwo);

//    Saving sale1 After setting all Users.
    saleRepository.save(sale1);

//    Saving sale2 After setting all Users.
    saleRepository.save(sale2);












    }

}
