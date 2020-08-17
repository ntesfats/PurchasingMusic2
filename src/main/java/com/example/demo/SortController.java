package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
public class SortController {

    @Autowired
    SongRepository songRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaleRepository saleRepository;

//    @RequestMapping("/index")
//    public String index(Principal principal, Model model) {
//        if (principal != null) {
//            model.addAttribute("currentUser", userRepository.
//                    findByUsername(principal.getName()));
//        }
//
//        return "index";
//    }


    @RequestMapping("/purchaseSongs")
    public String showPurchasedSongs(Model model, Principal principal){
        User currentUser = userRepository.findByUsername(principal.getName());
        List<Song> allPurchaseSongs = getAllPurchasedSongs(currentUser.getId());

        model.addAttribute("purchasedSongs", allPurchaseSongs);

        return "songPurchase";
    }


    @GetMapping("/purchaseSong/sortBy/songTitle")
    public String sortBySongTitle(@RequestParam boolean allSong, Model model, Principal principal){
        // if allSong is true: Sort all Purchased Songs. Otherwise: Sort All Songs.
        List<Song> sortedByTitle;

        if (allSong) {
            List<Song> sortAllSongs = songRepository.findAll();
            sortedByTitle = getAllPurchasedSongSortedByTitle(sortAllSongs);
        } else {
            long userId = getCurrUserId(principal);
            List<Song> sortPurchasedSongs = getAllPurchasedSongs(userId);
            sortedByTitle = getAllPurchasedSongSortedByTitle(sortPurchasedSongs);
        }

        model.addAttribute("sortedByTitle", sortedByTitle);
        return "sortPage";
    }

    @GetMapping("/purchaseSong/sortBy/songGenre")
    public String sortByGenre(@RequestParam boolean allSong, Model model, Principal principal){
        // if allSong is true: Sort all Purchased Songs. Otherwise: Sort All Songs.
        List<Song> sortedByGenre;
        if (allSong) {
            List<Song> sortAllSongs = songRepository.findAll();
            sortedByGenre = getAllPurchasedSongSortedByGenre(sortAllSongs);
        } else {
            long userId = getCurrUserId(principal);
            List<Song> sortPurchasedSongs = getAllPurchasedSongs(userId);
            sortedByGenre = getAllPurchasedSongSortedByGenre(sortPurchasedSongs);
        }

        model.addAttribute("sortedByGenre", sortedByGenre);
        return "sortPage";
    }

    @GetMapping("/purchaseSong/sortBy/albumName")
    public String sortByAlbumName(@RequestParam boolean allSong, Principal principal, Model model){
        // if allSong is true: Sort all Purchased Songs. Otherwise: Sort All Songs.
        List<Song> sortedByAlbum;
        if (allSong) {
            List<Song> sortAllSongs = songRepository.findAll();
            sortedByAlbum = getAllPurchasedSongSortedByAlbumName(sortAllSongs);
        } else {
            long userId = getCurrUserId(principal);
            List<Song> sortPurchasedSongs = getAllPurchasedSongs(userId);
            sortedByAlbum = getAllPurchasedSongSortedByAlbumName(sortPurchasedSongs);
        }

        model.addAttribute("sortedByAlbum", sortedByAlbum);
        return "sortPage";
    }
//
    @RequestMapping("/purchaseSong/sortBy/artistName")
    public String sortByArtistName(@RequestParam boolean allSong, Principal principal, Model model){
        // if allSong is true: Sort all Purchased Songs. Otherwise: Sort All Songs.
        List<Song> sortedByArtist;
        if (allSong) {
            /* To Make it easy for Us, I have used the same function as above to
             * sort both all and purchased Songs. We must still pass in an Id,
             * I have desgin the method where if the Id is below 0 then to sort All Songs.
             * So Input below zero Id into the getAllPurchasedSongSortedByArtistName argument.
             */

            // Sort all Songs in DataBase
            sortedByArtist = getAllPurchasedSongSortedByArtistName(-100);
        } else {
            long userId = getCurrUserId(principal);
            sortedByArtist = getAllPurchasedSongSortedByArtistName(userId);
        }

        model.addAttribute("sortedByArtist", sortedByArtist);
        return "sortPage";

    }
//
    @RequestMapping("/purchaseSong/sortBy/songDuration")
    public String sortByDuration(@RequestParam boolean allSong, Principal principal, Model model) {
        // if allSong is true: Sort All Songs. Otherwise: Sort all Purchased Songs.
        List<Song> sortedByDuration;
        if (allSong) {
            List<Song> sortAllSongs = songRepository.findAll();
            sortedByDuration = getAllPurchasedSongSortedByDuration(sortAllSongs);
        } else {
            long userId = getCurrUserId(principal);
            List<Song> sortPurchasedSongs = getAllPurchasedSongs(userId);
            sortedByDuration = getAllPurchasedSongSortedByDuration(sortPurchasedSongs);
        }

        model.addAttribute("sortedByDuration", sortedByDuration);
        return "sortPage";
    }


//    Sorting
    //    By: SongTitle

    private List<Song> getAllPurchasedSongSortedByTitle(List<Song> sortThisSongs) {
        sortThisSongs.sort((Song s1, Song s2) ->
                s1.getSongTitle().compareTo(s2.getSongTitle()) );
        return sortThisSongs;
    }
    //    By: SongGenre
    private List<Song> getAllPurchasedSongSortedByGenre(List<Song> sortThisSongs) {
        sortThisSongs.sort((Song s1, Song s2) ->
                s1.getSongGenre().compareTo(s2.getSongGenre()) );
        return sortThisSongs;
    }

    private List<Song> getAllPurchasedSongSortedByAlbumName(List<Song> sortThisSongs) {
        sortThisSongs.sort((Song s1, Song s2) ->
                s1.getSongAlbum().getAlbumName().compareTo(s2.getSongAlbum().getAlbumName()) );
        return sortThisSongs;
    }

    private List<Song> getAllPurchasedSongSortedByArtistName(long id) {
        List<Song> sortedByArtistNameSongs;
//        This means Sort All Purchased Songs by artistName.
        if (id >= 0) {
            User user = userRepository.findById(id).get();

            Iterable<Sale> allSales = user.getSales();

            sortedByArtistNameSongs = new ArrayList<Song>();
            for (Sale sale : allSales) {
                if (sale.getIsPurchase()) {
                    List<Artist> allArtistWithInASong = new ArrayList<>();
                    for (Song song : sale.getSongs()) {
                        allArtistWithInASong = song.getArtists();
                        allArtistWithInASong.sort((Artist a1, Artist a2) ->
                                (a1.getArtistName().compareTo(a2.getArtistName())));
                        song.setArtists(allArtistWithInASong);
//                           artistRepository.findAllBySong(song.getId())
                    }

                    sortedByArtistNameSongs.addAll(sale.getSongs());

                }
            }
//            Else Sort All the songs in DataBases by artistName;
        } else {
            sortedByArtistNameSongs = songRepository.findAll();
        }

        sortedByArtistNameSongs.sort((Song s1, Song s2) ->
                s1.getArtists().get(0).getArtistName().compareTo(s2.getArtists().get(0).getArtistName()) );
        return sortedByArtistNameSongs;
    }

    private List<Song> getAllPurchasedSongSortedByDuration(List<Song> sortThisSongs) {
        sortThisSongs.sort((Song s1, Song s2) ->
                s1.getSongDuration().compareTo(s2.getSongDuration()) );
        return sortThisSongs;
    }


    private List<Song> getAllPurchasedSongs(long id) {
        User user = userRepository.findById(id).get();
        Iterable<Sale> allSales = user.getSales();

        List<Song> allPurchaseSongs = new ArrayList<Song>();
        for (Sale sale : allSales) {
            if (sale.getIsPurchase()) {
                allPurchaseSongs.addAll(sale.getSongs());
            }
        }
        return allPurchaseSongs;
    }

    private User getCurrentUser(Principal principal) {
        String userName = principal.getName();
        User currentUser = userRepository.findByUsername(userName);

        return currentUser;
    }
    private long getCurrUserId(Principal principal) {
        User currentUser = getCurrentUser(principal);

        return currentUser.getId();
    }







}
