package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @RequestMapping("purchaseSongs/{id}")
    public String showPurchasedSongs(@PathVariable("id")long id, Model model){
        List<Song> allPurchaseSongs = getAllPurchasedSongs(id);

        model.addAttribute("purchasedSongs", allPurchaseSongs);

        return "songPurchase";
    }


    @RequestMapping("/purchaseSong/sortBy/songTitle")
    public String sortBySongTitle(Model model, Principal principal){
        User currentUser = userRepository.findByUsername(principal.getName());
        long userId = currentUser.getId();
        List<Song> allPurchaseSongs = getAllPurchasedSongs(userId);

        List<Song> sortedByTitle = getAllPurchasedSongSortedByTitle(allPurchaseSongs);
        model.addAttribute("sortedByTitle", sortedByTitle);
        return "sortPage";
    }


//   /*
//    //@RequestMapping("/purchaseSong/sortBy/songGenre")
//    public String sortByGenre(@PathVariable String songGenre, Model model){
//
//    }
//
//    //@RequestMapping("/purchaseSong/sortBy/albumName")
//    public String sortByAlbumName(@PathVariable String songGenre, Model model){
//
//    }
//
//    //@RequestMapping("/purchaseSong/sortBy/artistName")
//    public String sortByArtistName(@PathVariable String songGenre, Model model){
//
//    //}
//
//    //@RequestMapping("/purchaseSong/sortBy/songDuration")
//    //public String sortByDuration(@PathVariable String songGenre, Model model){
//
//    //}*/

    private List<Song> getAllPurchasedSongs(long id) {
        User user = userRepository.findById(id).get();

        Iterable<Sale> allSales = user.getSales();

        List<Song> allPurchaseSongs = new ArrayList<Song>();

        for (Sale sale : allSales) {

            if (sale.getIsPurchase()) {
//                allPurchaseSongs.addAll(songRepository.findAllBySalesOrderBySongTitle(sale));
                allPurchaseSongs.addAll(sale.getSongs());
            }
        }

        return allPurchaseSongs;
    }

    private List<Song> getAllPurchasedSongSortedByTitle(List<Song> allPurchaseSongs) {
        allPurchaseSongs.sort((Song s1, Song s2) ->
                s1.getSongTitle().compareTo(s2.getSongTitle()) );
        return allPurchaseSongs;
    }

    /*private HashSet<Song> sortSongsBy(HashSet<Song> allSongs, String by) {
        if (by.equals("genre")) {

        }
    }*/







}
