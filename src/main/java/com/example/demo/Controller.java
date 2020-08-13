package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String home() {
        return"Home";
    }
    @RequestMapping("/ArtistPage")
    public String ArtistPage(){
        return "ArtistPage";
    }
}
