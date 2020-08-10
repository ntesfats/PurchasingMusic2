package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SaleRepository saleRepository;

    public void run(String... args){


    User admin = new User("admin", "admin@gmail.com","admin", "admin", "admin", true);
    User userOne = new User("user", "user@gmail.com", "password", "User", "One", true);

    Role adminRole = new Role("admin", "ROLE_ADMIN");
    Role userRole = new Role("user", "ROLE_USER");



    userRepository.save(admin);
    roleRepository.save(adminRole);

    userRepository.save(userOne);
    roleRepository.save(userRole);

    Song songOne = new Song("songTitle", "songGenre", "songAlbum", "2:00");
    songRepository.save(songOne);

    Artist artistOne = new Artist("artistOneName", songOne);

    artistRepository.save(artistOne);

    Sale firstSale = new Sale("songTitle", 5.00);

    Set<User> user = new HashSet<>();
    user.add(userOne);
    firstSale.setUsers(user);

    saleRepository.save(firstSale);












    }

}
