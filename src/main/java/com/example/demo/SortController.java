package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

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



    @RequestMapping("purchaseSongs/{id}")
    public String showPurchasedSongs(@PathVariable("id")long id, Model model){
        HashSet<Song> allPurchaseSongs = getAllPurchasedSongs(id);


        model.addAttribute("purchasedSongs", allPurchaseSongs);

        return "songPurchase";
    }


    @RequestMapping("/purchaseSong/sortBy/songTitle")
    public String sortBySongTitle(@PathVariable String songGenre, Model model){

        //model.addAttribute("sortedByTitle", );
        return "sortPage";
    }


   /*
    @RequestMapping("/purchaseSong/sortBy/songGenre")
    public String sortByGenre(@PathVariable String songGenre, Model model){

    }

    @RequestMapping("/purchaseSong/sortBy/albumName")
    public String sortByAlbumName(@PathVariable String songGenre, Model model){

    }

    @RequestMapping("/purchaseSong/sortBy/artistName")
    public String sortByArtistName(@PathVariable String songGenre, Model model){

    }

    @RequestMapping("/purchaseSong/sortBy/songDuration")
    public String sortByDuration(@PathVariable String songGenre, Model model){

    }*/

    private HashSet<Song> getAllPurchasedSongs(long id) {
        User user = userRepository.findById(id).get();

        Iterable<Sale> allSales = user.getSales();

        HashSet<Song> allPurchaseSongs = new HashSet<Song>();

        for (Sale sale : allSales) {
            if (sale.getIsPurchase() == true) {
                for (Song song : sale.getSongs()) {
                    allPurchaseSongs.add(song);
                }

            }
        }
        return allPurchaseSongs;
    }

    /*private HashSet<Song> sortSongsBy(HashSet<Song> allSongs, String by) {
        if (by.equals("genre")) {

        }
    }*/







}
