package com.example.newscrawler.controller;

import com.example.newscrawler.model.Item;
import com.example.newscrawler.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public String redirectToNews() {
    return "redirect:/news";
  }

  @SuppressWarnings("CheckStyle")
  @GetMapping("/news")
  public String getNews(Model model, @PageableDefault(sort = {"pubDate"},
          direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String filter) {
    Page<Item> news;
    if (filter != null && !filter.isEmpty()) {
      news = itemRepository.findByTitleIgnoreCaseContaining(filter, pageable);
    } else {
      news = itemRepository.findAll(pageable);
    }
    model.addAttribute("filter", filter);
    model.addAttribute("page", news);
    return "index";
  }

  @GetMapping("/news/{id}")
  public Item getNewsItem(@PathVariable(name = "id") Long id) {
    return itemRepository.findById(id).orElse(null);
  }

}
