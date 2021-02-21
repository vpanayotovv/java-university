package com.exam.springexam.service;

import com.exam.springexam.model.binding.AlbumAddBindingModel;
import com.exam.springexam.model.view.AlbumViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {
    void add(AlbumAddBindingModel albumAddBindingModel);

    Long getTotalCopies();

    List<AlbumViewModel> findAllSorted();

    void deleteById(Long id);
}
