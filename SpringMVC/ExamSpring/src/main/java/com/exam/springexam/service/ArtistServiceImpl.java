package com.exam.springexam.service;

import com.exam.springexam.model.entity.ArtistEntity;
import com.exam.springexam.model.entity.enums.ArtistName;
import com.exam.springexam.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void initData() {
        if (this.artistRepository.count() == 0) {
            Arrays.stream(ArtistName.values())
                    .forEach(artist -> createArtist(artist, String.format("This is the %S artist Information", artist.name())));
        }
    }

    @Override
    public ArtistEntity getByName(ArtistName artist) {
        return artistRepository.findByName(artist).orElse(null);
    }

    private void createArtist(ArtistName name, String information) {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName(name);
        artistEntity.setCareerInformation(information);
        artistRepository.saveAndFlush(artistEntity);
    }
}
