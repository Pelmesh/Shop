package com.nc.finalProject.service.impl;

import com.nc.finalProject.model.Item;
import com.nc.finalProject.repo.ItemRepository;
import com.nc.finalProject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> create(List<Item> itemList) {
        return itemRepository.saveAll(itemList);
    }
}
