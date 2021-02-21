package com.exam.springexam.service;

import com.exam.springexam.model.binding.AlbumAddBindingModel;
import com.exam.springexam.model.entity.AlbumEntity;
import com.exam.springexam.model.view.AlbumViewModel;
import com.exam.springexam.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
    }

    @Override
    public void add(AlbumAddBindingModel albumAddBindingModel) {
        AlbumEntity albumEntity = modelMapper.map(albumAddBindingModel, AlbumEntity.class);
        albumEntity.setArtist(artistService.getByName(albumAddBindingModel.getArtist()));
        albumRepository.saveAndFlush(albumEntity);
    }

    @Override
    public Long getTotalCopies() {
        return albumRepository.getTotalCopies() != null ? albumRepository.getTotalCopies() : 0;
    }

    @Override
    public List<AlbumViewModel> findAllSorted() {
        return albumRepository.getAllSorted().stream().map(album -> modelMapper.map(album, AlbumViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
