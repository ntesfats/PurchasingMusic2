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
//
//    @RequestMapping("/")
//    public String home(Model model) {
//        model.addAttribute("songs",songRepository.findAll());
//        return"Home";
//    }
    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("songs",songRepository.findAll());
        if(principal!=null) {
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomerPost(@ModelAttribute("user") User user, Model model){
        List<User> userList = user.getAllUsers();
        for(int i = 0; i< userList.size(); i++){
            if (user.getEmail().equals(userList.get(i).getUserEmail())){
                model.addAttribute("emailMsg", "Email already exists");
                return "register";
             }

            if(user.getUsername().equals(userList.get(i).getUserName())){
                model.addAttribute("usernameMsg","Username already exists");
                return "register";
            }

    @PostMapping("/processregister")
    public String processRegistrationPage(@ModelAttribute User user, Model model){
        userRepository.save(user);
        model.addAttribute("message","New user account created");
        return "redirect:/";
    }
            user.setEnabled(true);
            userRepository.save(user);
            Role role = new Role(user.getUsername(),"ROLE_USER");
            roleRepository.save(role);
        }
        return "home";
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

>>>>>>> d5c7c813c1ec30a081578bbd7e8780aea0965d66
}
