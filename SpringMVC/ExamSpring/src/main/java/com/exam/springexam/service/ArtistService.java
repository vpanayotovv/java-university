package com.exam.springexam.service;

import com.exam.springexam.model.entity.ArtistEntity;
import com.exam.springexam.model.entity.enums.ArtistName;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {
    void initData();

    ArtistEntity getByName(ArtistName artist);
}
