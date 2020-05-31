package com.example.newscrawler.repository;

import com.example.newscrawler.model.Item;
import com.example.newscrawler.model.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByTitleIgnoreCaseContaining(String title, Pageable pageable);

    Page<Item> findAll(Pageable pageable);

    Item findFirstBySourceOrderByPubDateDesc(Source source);

}
