package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    UserRepository userRepository;


//  Home Page
    @RequestMapping("/index")
    public String index(Principal principal, Model model) {
        if (principal != null) {
            String currentUserName = principal.getName();

            User user = userRepository.findByUsername(currentUserName);

            model.addAttribute("currentUserName", currentUserName);
            model.addAttribute("currentUser", user);
        }

        return"index";
    }

//  Login Page
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//  Logout Page
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }

// Display artist by ID
    @RequestMapping("/artist/{id}")
    public String showArtistbyID(@PathVariable("id")long id, Model model){
        model.addAttribute("artist", artistRepository.findById(id).get());
        return ""; // add the artist html
    }

//Display Song by Song Genre
    @RequestMapping("/genre/{genreName}")
    public String showSongbyGenre(@PathVariable("genreName")String genreName, Model model){
        model.addAttribute("songs", songRepository.findSongBysongGenre(genreName));


        return ""; // Show song by genre
    }




}
