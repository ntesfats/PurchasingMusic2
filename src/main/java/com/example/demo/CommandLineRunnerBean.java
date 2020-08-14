package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        Artist destinyschild = new Artist("Destiny's Child", "Destiny's Child description");
        destinyschild.setHeadShotUrl("https://akns-images.eonline.com/eol_images/Entire_Site/2018116/rs_1080x1080-180216142815-destinys-child-album-cover.jpg?fit=around|1080:auto&output-quality=90&crop=1080:auto;center,top.jpg");
        artistRepository.save(destinyschild);

        Artist aliciakeys = new Artist("Alicia Keys", "Alicia Keys description");
        aliciakeys.setHeadShotUrl("https://www.gstatic.com/tv/thumb/persons/280087/280087_v9_bb.jpg");
        artistRepository.save(aliciakeys);


        Artist missyelliott = new Artist("Missy Elliott", "Missy Elliott description");
        missyelliott.setHeadShotUrl("https://upload.wikimedia.org/wikipedia/commons/7/77/Missy_Elliot.jpg");
        artistRepository.save(missyelliott);


        Artist taylorswift = new Artist("TLC", "TLC description");
        taylorswift.setHeadShotUrl("https://hips.hearstapps.com/harpersbazaaruk.cdnds.net/17/19/1494325337-tlc.jpg");
        artistRepository.save(taylorswift);


        Artist harrystyles = new Artist("Pryianka Chopra", "Priyanka Chopra");
        harrystyles.setHeadShotUrl("https://i.insider.com/5d2f3bc6ba15961b544b3e64?width=600&format=jpeg&auto=webp");
        artistRepository.save(harrystyles);




        //  Creating Album
        Album scorpionAlbum = new Album("Scorpion", 2020, "Scorpion description", drake);
        scorpionAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");


        Album writingsOnTheWallAlbum = new Album("The Writings On the Wall", 1999, "The Writing's on the Wall description", destinyschild);
        writingsOnTheWallAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");

        Album theDiaryOfAliciaKeysAlbum = new Album("The Diary of Alicia Keys", 2003, "The Diary of Alicia Keys description", aliciakeys);
        theDiaryOfAliciaKeysAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");

        Album underConstructionAlbum = new Album("Under Construction", 2002, "Under Construction description", missyelliott);
        underConstructionAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");

        Album folkloreAlbum = new Album("Folklore", 2020, "Folklore description", taylorswift);
        folkloreAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");

        Album fineLineAlbum = new Album("Fine Line", 2019, "FineLine description", harrystyles);
        fineLineAlbum.setAlbumCoverUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597249772/Scorpion_ijyxyc.jpg");



//  Saving Album
        albumRepository.save(scorpionAlbum);
        albumRepository.save(writingsOnTheWallAlbum);
        albumRepository.save(theDiaryOfAliciaKeysAlbum);
        albumRepository.save(underConstructionAlbum);
        albumRepository.save(folkloreAlbum);
        albumRepository.save(fineLineAlbum);

//  Creating Songs
        Song survivalSong = new Song("Survival", "Hip-Hop/Rap", "2:16",
                2018, .99, scorpionAlbum,"https://www.youtube.com/embed/QmPjOp9A0g4");
        survivalSong.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250219/survivalSong_rituus.jpg");

        Song talkUpSong = new Song("Talk Up (feat. JAY-Z)", "Rap", "3:43",
                2018, 1.99, scorpionAlbum,"https://www.youtube.com/embed/jnzBYqOWJ5U");
        talkUpSong.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song1 = new Song("Billie Jean", "R&B", "4:54",
                1983, 1.99, scorpionAlbum,"https://youtu.be/Kr4EQDVETuA");
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song2 = new Song("Bills, Bills, Bills", "R&B", "4:17",
                1999, 1.99, writingsOnTheWallAlbum,"https://www.youtube.com/embed/zhtd_Ga5xHA?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song3 = new Song("If I Ain't Got You", "R&B", "3:51",
                2003, 1.99, theDiaryOfAliciaKeysAlbum,"https://www.youtube.com/embed/Kr4EQDVETuA?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song4 = new Song("Work It", "R&B", "4:25",
                2002, 1.99, underConstructionAlbum,"https://www.youtube.com/embed/Mijm6hL87y8?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song5 = new Song("Cardigan", "Pop", "3:35",
                2020, 1.99, folkloreAlbum,"https://www.youtube.com/embed/aVRcXtbbh_w?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");


        Song song7 = new Song("Watermelon Sugar", "Pop", "2:53",
                1994, 1.99, fineLineAlbum,"https://www.youtube.com/embed/7-x3uD5z1bQ?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");

        Song song8 = new Song("In My City", "Pop", "3:45",
                2013, 1.99, fineLineAlbum,"https://www.youtube.com/embed/Zjgq6-5uDtY?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1"
        );
        song1.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250297/elevateSong_hixxk8.jpg");



        songRepository.save(song1);
        songRepository.save(song2);
        songRepository.save(song3);
        songRepository.save(song4);
        songRepository.save(song5);
        songRepository.save(song7);
        songRepository.save(song8);



        songRepository.save(survivalSong);
        songRepository.save(talkUpSong);


//   Creating Set of Artist
        List<Artist> artistSetForSurvival = new ArrayList<>();
        artistSetForSurvival.add(drake);

        List<Artist> artistSetForTalkUp = new ArrayList<>();
        artistSetForSurvival.add(drake);
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


        Set<Song> userTwoSale2AllSong = new HashSet<>();
        userTwoSale2AllSong.add(talkUpSong);


//    Creating Sale and setting the Song that are purchased
        Sale sale1 = new Sale(nahom, 5.0, true);
        sale1.setSongs(nahomFirstSaleAllSong);
        Sale sale11 = new Sale(nahom, 4.0, true);
        sale11.setSongs(nahomSecondSaleAllSong);

        Sale sale2 = new Sale(userTwo,2.0, true);
        sale2.setSongs(userTwoSale2AllSong);

//    Saving Sole1 and Sale2
        saleRepository.save(sale1);
        saleRepository.save(sale2);
        saleRepository.save(sale11);

////    Auto Generated Songs
//        autoGeneratedData(scorpionAlbum, drake);
//
    }



//    //    Auto Generated Songs
//    private void autoGeneratedData(Album scorpionAlbum, Artist drake) {
//        for (int i = 0; i < 5; i++) {
//
//            //  Creating Songs
//            Song survivalSong = new Song("Survival", "Hip-Hop/Rap", "2:16",
//                    2018, .99, scorpionAlbum, "https://www.youtube.com/embed/QmPjOp9A0g4?showinfo=0&fs=0&autohide=0&controls=0&disablekb=1");
//            survivalSong.setSongImageUrl("https://res.cloudinary.com/dxiriemba/image/upload/v1597250219/survivalSong_rituus.jpg");
//
//            songRepository.save(survivalSong);
//
//
//            //   Creating Set of Artist
//            List<Artist> artistSetForSurvival = new ArrayList<>();
//            artistSetForSurvival.add(drake);
//
//
//            //   Setting up all the artist that are in the song
//            survivalSong.setArtists(artistSetForSurvival);
//
//            //  Saving the Song
//            songRepository.save(survivalSong);
//        }
//    }

}