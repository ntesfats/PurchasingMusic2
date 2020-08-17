package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

/***********************************    Artist: Add, Update  ******************************************/
    @GetMapping("/admin/artist/add")
    public String loadArtistForm(Model model) {
        model.addAttribute("artist", new Artist());
        return "addArtist";
    }

    //Update Artist
    @GetMapping("/admin/artist/update/{id}")
    public String updateArtist(@PathVariable long id, Model model) {
        Artist artist = artistRepository.findById(id).get();

        model.addAttribute("artist", artist);
        return "addArtist";
    }

    //Artist Form Processing
    @PostMapping("/admin/artist/process")
    public String processArtistForm(@ModelAttribute Artist artist, BindingResult result,
                                    @RequestParam("file") MultipartFile file) {
//        First we check if the file sibmitted is empty
        if (file.isEmpty() || result.hasErrors()) {
            return "redirect:/admin/artist/add";
        }
//        Then we upload the fileto cloudinary
        try {
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            artist.setHeadShotUrl(uploadResult.get("url").toString());
            artistRepository.save(artist);
//            We check if there was any error during upload; if so, redirect to the /add opage
        } catch (IOException e) {

            e.getStackTrace();
            return "redirect:/admin/artist/add";
        }

//        If Everything went okay \, we redirect to the home page
        artistRepository.save(artist);
        return "redirect:/";
    }


    // Add song form
    @GetMapping("/admin/song/add")
    public String loadSongForm(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("song", new Song());
        return "addSong";
    }

    // Song processing
    @PostMapping("/admin/song/process")
    public String processSongForm(@ModelAttribute Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "addSong";
        }

        songRepository.save(song);
        return "redirect:/";
    }

    @GetMapping("/admin/album/add")
    public String addAlbum(Model model) {
        //  Need to Save Album Cover into DataBase first.
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("album", new Album());
        return "addAlbum";
    }

     // Album processing
    @PostMapping("/admin/album/process")
    public String processAlbumForm(@ModelAttribute Album album, BindingResult result) {
        if (result.hasErrors()) {
            return "addAlbum";
        }

        albumRepository.save(album);
        return "redirect:/";
    }


}
