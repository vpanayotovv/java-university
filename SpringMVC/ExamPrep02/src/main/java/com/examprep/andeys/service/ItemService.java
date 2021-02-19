package com.examprep.andeys.service;

import com.examprep.andeys.model.binding.ItemAddBindingModel;
import com.examprep.andeys.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    void add(ItemAddBindingModel itemAddBindingModel);

    List<ItemViewModel> findAllItems();
}
