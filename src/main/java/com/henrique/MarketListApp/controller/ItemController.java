package com.henrique.MarketListApp.controller;

import com.henrique.MarketListApp.model.Item;
import com.henrique.MarketListApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/items/addItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Item add(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}
