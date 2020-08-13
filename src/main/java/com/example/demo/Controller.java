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
        model.addAttribute("user",new User());

        return"register";
    }
    @PostMapping("/processregister")
    public String processRegisterationPage(
            @Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user",user);
            return "register";
        }
        else {
            model.addAttribute("user",user);
            model.addAttribute("message","New user account created");

            user.setEnabled(true);
            userRepository.save(user);
            Role role = new Role(user.getUsername(),"ROLE_USER");
            roleRepository.save(role);
        }
        return "index";
    }
    @RequestMapping("/ArtistPage")
    public String ArtistPage(){
        return "ArtistPage";
    }
}
