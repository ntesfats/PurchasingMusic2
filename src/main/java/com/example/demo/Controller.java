package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ArtistRepository artistRepository;

    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("songs", songRepository.findAll());
        if (principal != null) {
            User currentUser = userRepository.findByUsername(principal.getName());
            model.addAttribute("currentUser", currentUser);
        }
        return "home";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }


    @RequestMapping("/ArtistPage")
    public String ArtistPage() {
        return "ArtistPage";
    }

    @RequestMapping("/processregister")
    public String processRegistrationPage(@ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }
        userRepository.save(user);
        model.addAttribute("message", "New user account created");

        user.setEnabled(true);
        userRepository.save(user);
        Role role = new Role(user.getUsername(), "ROLE_USER");
        roleRepository.save(role);

        return "redirect:/";
    }


    @RequestMapping("/purchase/song/{id}")
    public String purchaseSong(@PathVariable long id, Principal principal, Model model) {
        User currentUser = userRepository.findByUsername(principal.getName());
        Song purchaseSong = songRepository.findById(id).get();
        double totalPrice = purchaseSong.getSongPrice();
        Sale newSale = new Sale(currentUser, totalPrice, true);
        HashSet<Song> allPurchaseSong = new HashSet<>();
        allPurchaseSong.add(purchaseSong);
        newSale.setSongs(allPurchaseSong);
        saleRepository.save(newSale);

        return "redirect:/";
    }


}
