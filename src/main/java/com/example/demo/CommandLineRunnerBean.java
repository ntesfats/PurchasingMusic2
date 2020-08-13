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
        User admin = new User("admin", "admin@gmail.com","admin", "admin", "admin", true,4444);

        //  User or Customer that have ability to purchase a music
        User nahom = new User("nahom", "nahom@gmail.com", "nahom", "Nahom", "Tesfatsion", true,2222);
        User userTwo = new User("user2", "user2@gmail.com", "password", "User2", "Two", true,5555);

        Role adminRole = new Role("admin", "ROLE_ADMIN");
        Role nahomRole = new Role("nahom", "ROLE_USER");
        Role userRole2 = new Role("user2", "ROLE_USER");



        userRepository.save(admin);
        roleRepository.save(adminRole);

        userRepository.save(nahom);
        userRepository.save(userTwo);

        roleRepository.save(nahomRole);
        roleRepository.save(userRole2);

//    Creating Artist
        Artist drake = new Artist("Drake", "Drake description");
        drake.setHeadShotUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597248855/drakeHeadShot_bpsfk8.jpg");
        artistRepository.save(drake);

        Artist jayZ = new Artist("JAY-Z", "Jay-Z description");
        jayZ.setHeadShotUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597255995/jayZHeadShot_gknpay.jpg");
        artistRepository.save(jayZ);

//        Artist usher = new Artist("Usher", "Usher description");
//        usher.setHeadShotUrl();
//        artistRepository.save(usher);
//
//        Artist taylorswift = new Artist("Taylor Swift", "Taylor Swift description");
//        taylorswift.setHeadShotUrl();
//        artistRepository.save(taylorswift);
//
//        Artist harrystyles = new Artist("Harry Styles", "Harry Styles description");
//        harrystyles.setHeadShotUrl();
//        artistRepository.save(harrystyles);
//
//        Artist michaeljackson = new Artist("Michael Jackson", "Michael Jackson description");
//        michaeljackson.setHeadShotUrl();
//        artistRepository.save(michaeljackson);


        //  Creating Album
        Album scorpionAlbum = new Album("Scorpion", 2020, "Scorpion description", drake);
        scorpionAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");

//  Saving Album
        albumRepository.save(scorpionAlbum);

//  Creating Songs
        Song survivalSong = new Song("Survival", "Hip-Hop/Rap", "2:16",
                2018, .99, scorpionAlbum,"https://www.youtube.com/embed/QmPjOp9A0g4?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1");
        survivalSong.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250219/survivalSong_rituus.jpg");

        Song talkUpSong = new Song("Talk Up (feat. JAY-Z)", "Hip-Hop/Rap", "3:43",
                2018, 1.99, scorpionAlbum,"https://www.youtube.com/embed/QmPjOp9A0g4?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1");
        talkUpSong.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");

        songRepository.save(survivalSong);
        songRepository.save(talkUpSong);


//   Creating Set of Artist
        Set<Artist> artistSetForSurvival = new HashSet<>();
        artistSetForSurvival.add(drake);

        Set<Artist> artistSetForTalkUp = new HashSet<>();
        artistSetForTalkUp.add(drake);
        artistSetForTalkUp.add(jayZ);

//   Setting up all the artist that are in the song
        survivalSong.setArtists(artistSetForSurvival);
        talkUpSong.setArtists(artistSetForTalkUp);

//  Saving the Song
        songRepository.save(survivalSong);
        songRepository.save(talkUpSong);

//  Creating Set of Song, (All the song the Nahom or other User are purchasing)
        Set<Song> nahomFirstSaleAllSong = new HashSet<>();
        Set<Song> nahomSecondSaleAllSong = new HashSet<>();
        nahomFirstSaleAllSong.add(survivalSong);
        nahomFirstSaleAllSong.add(talkUpSong);
        nahomSecondSaleAllSong.add(talkUpSong);

        Set<Song> userTwoSale2AllSong = new HashSet<>();
        userTwoSale2AllSong.add(survivalSong);


//    Creating Sale and setting the Song that are purchased
        Sale sale1 = new Sale(nahom, 5.0, false);
        sale1.setSongs(nahomFirstSaleAllSong);
        Sale sale11 = new Sale(nahom, 4.0, false);
        sale11.setSongs(nahomSecondSaleAllSong);

        Sale sale2 = new Sale(userTwo,2.0, true);
        sale2.setSongs(userTwoSale2AllSong);

//    Saving Sole1 and Sale2
        saleRepository.save(sale1);
        saleRepository.save(sale2);
        saleRepository.save(sale11);

    }

}