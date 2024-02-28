package com.henrique.MarketListApp.controller;

import com.henrique.MarketListApp.model.Item;
import com.henrique.MarketListApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @GetMapping
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item id not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item add(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> update(@PathVariable Long itemId, @RequestBody Item item) {

        if(!itemRepository.existsById(itemId)) {
            return ResponseEntity.notFound().build();
        }

        item.setId(itemId);
        item = itemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{itemId}")
        public ResponseEntity<Void> delete(@PathVariable Long itemId) {

            if(!itemRepository.existsById(itemId)) {
                return ResponseEntity.notFound().build();
            }

            itemRepository.deleteById(itemId);
            return ResponseEntity.noContent().build();
    }
}
