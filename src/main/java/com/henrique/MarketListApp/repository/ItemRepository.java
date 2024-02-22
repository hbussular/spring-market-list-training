package com.henrique.MarketListApp.repository;

import com.henrique.MarketListApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
