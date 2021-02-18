package com.examprep.andeys.service;

import com.examprep.andeys.model.binding.ItemAddBindingModel;
import com.examprep.andeys.model.entity.Item;
import com.examprep.andeys.model.entity.enums.GenderName;
import com.examprep.andeys.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void add(ItemAddBindingModel itemAddBindingModel) {
        Item item = modelMapper.map(itemAddBindingModel,Item.class);
        item.setCategory(categoryService.findByName(itemAddBindingModel.getCategory()));
        item.setGender(GenderName.valueOf(itemAddBindingModel.getGender().name()));

        itemRepository.saveAndFlush(item);
    }
}
