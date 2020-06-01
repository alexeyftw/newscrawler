package com.example.newscrawler.controller;

import java.util.List;
import com.example.newscrawler.model.Source;
import com.example.newscrawler.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SourceController {

  @Autowired
  private SourceRepository sourceRepository;

  @PostMapping("/source/create")
  public Long createSource(@RequestBody Source source) {
    return sourceRepository.save(source).getId();
  }

  @GetMapping("/source/all")
  public List<Source> getAllSources() {
    return sourceRepository.findAll();
  }

}
