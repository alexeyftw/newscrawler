package com.example.newscrawler.service;

import com.example.newscrawler.exception.DocumentNotFoundException;
import com.example.newscrawler.model.Source;
import com.example.newscrawler.repository.SourceRepository;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsCrawlService {

  @Autowired
  private SourceRepository sourceRepository;
  @Autowired
  private HtmlFeedProcessor htmlFeedProcessor;
  @Autowired
  private RssFeedProcessor rssFeedProcessor;

  private static final String HTML = "html";
  private static final String RSS = "rss";
  private final Logger logger = LoggerFactory.getLogger(NewsCrawlService.class);

  public void findNews() {
    sourceRepository.findAll().forEach(this::handleSource);
  }

  private void handleSource(Source source) {
    Document document = getSourceDocument(source);
    if (document == null) {
      throw new DocumentNotFoundException(source.getUri());
    }

    switch (source.getContentType()) {
      case HTML:
        htmlFeedProcessor.processHtmlDocument(document, source);
        break;
      case RSS:
        rssFeedProcessor.processRssDocument(document, source);
        break;
      default:
        break;
    }

  }

  private Document getSourceDocument(Source source) {
    try {
      return Jsoup.connect(source.getUri()).get();
    } catch (IOException e) {
      logger.error("Couldn't get document from uri: {}", source.getUri());
    }
    return null;
  }
}
