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


//  Home Page
    @RequestMapping("/index")
    public String index(Principal principal, Model model) {
        model.addAttribute("currentUser", principal.getName());
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


}
