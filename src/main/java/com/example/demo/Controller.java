package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;


@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("songs",songRepository.findAll());
        return"Home";
    }
    @RequestMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return"registerUser";
    }
    @PostMapping("/processregister")
    public String processRegistrationPage(@ModelAttribute User user){
        userRepository.save(user);
        userid = user.getId();
        return "index";
    }
    @RequestMapping("/ArtistPage")
    public String ArtistPage(){
        return "ArtistPage";
    }
}
