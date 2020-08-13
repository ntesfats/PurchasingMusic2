package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

public class RegistrationController {

    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SaleRepository saleRepository;

    @RequestMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomerPost(@ModelAttribute("user") User user, Model model) {
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            if (user.getEmail().equals(userList.get(i).getEmail())) {
                model.addAttribute("emailMsg", "Email already exists");
                return "register";
            }

            if (user.getUsername().equals(userList.get(i).getUsername())) {
                model.addAttribute("usernameMsg", "Username already exists");
                return "register";
            }
        }
        model.addAttribute("message", "New user account created");

        user.setEnabled(true);
        userRepository.save(user);
        Role role = new Role(user.getUsername(), "ROLE_USER");
        roleRepository.save(role);

        return "redirect:/";
    }
}

