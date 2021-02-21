package com.exam.springexam.init;

import com.exam.springexam.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final ArtistService artistService;

    @Autowired
    public DbInit(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args){
        artistService.initData();
    }
}
