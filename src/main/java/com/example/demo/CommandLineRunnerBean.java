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

    @Autowired
    AlbumRepository albumRepository;

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

//    Creating Artist
    Artist artistOne = new Artist("artistOneName", "description");
    artistOne.setHeadShotUrl("artistHeadShotUrl");
    artistRepository.save(artistOne);

    Artist artistTwo = new Artist("artistTwoName", "description");
    artistTwo.setHeadShotUrl("artistHeadShotUrl");
    artistRepository.save(artistTwo);

//  Creating Album
    Album albumOne = new Album("albumOne", 2020, "album description",
            "album Cover Url", artistOne);

//  Saving Album
    albumRepository.save(albumOne);

//  Creating Songs
    Song songOne = new Song("songTitle", "songGenre", "2:00",
            2020, albumOne);
    songOne.setSongImageUrl("songOneUrl");
    Song songTwo = new Song("songTitle2", "songGenre2", "4:00",
            2019, albumOne);
    songTwo.setSongImageUrl("songTeoUrl");

    songRepository.save(songOne);
    songRepository.save(songTwo);

/*  // Creating Set of Songs
    Set<Song> songSet = new HashSet<>();
    songSet.add(songOne);
    songSet.add(songTwo);

  // Setting up all the song artistOne have created.
    artistOne.setSongs(songSet);
   // Saving Artist
    artistRepository.save(artistOne);
 */

//   Creating Set of Artist
     Set<Artist> artistSetForSongOne = new HashSet<>();
     artistSetForSongOne.add(artistOne);

     Set<Artist> artistSetForSongTwo = new HashSet<>();
     artistSetForSongTwo.add(artistOne);
     artistSetForSongTwo.add(artistTwo);

//   Setting up all the artist that are in the song
    songOne.setArtists(artistSetForSongOne);
    songTwo.setArtists(artistSetForSongTwo);

//  Saving the Song
    songRepository.save(songOne);
    songRepository.save(songTwo);


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
