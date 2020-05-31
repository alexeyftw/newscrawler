package com.example.newscrawler.service;

import com.example.newscrawler.model.Item;
import com.example.newscrawler.model.Source;
import com.example.newscrawler.repository.ItemRepository;
import com.example.newscrawler.util.DateTimeParserUtil;
import com.example.newscrawler.util.PreviewCreatorUtil;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HtmlFeedProcessor {

  private final Logger logger = LoggerFactory.getLogger(HtmlFeedProcessor.class);

  @Autowired
  private ItemRepository itemRepository;

  void processHtmlDocument(Document document, Source source) {
    Elements elements = document.select(source.getNewsContainerTag());
    List<Item> items = elements.stream()
            .map(element -> buildItem(element, source))
            .filter(i -> i.getTitle() != null && !i.getTitle().isBlank())
            .collect(Collectors.toList());
    itemRepository.saveAll(items);
  }

  private Item buildItem(Element element, Source source) {

    String preview = PreviewCreatorUtil.createPreview(element, source);
    String timeStamp = element.select(source.getPubDateTag()).attr(source.getPubDateAttribute());
    if (timeStamp.isBlank()) {
      return new Item();
    } // скипаем промопосты/твиты // todO: мб не лучшее решение(попахивает костылем)))0
    Date pubDate = DateTimeParserUtil.parseTimestamp(timeStamp, source.getPubDateFormat());

    return Item.builder()
            .title(element.select(source.getTitleTag()).text())
            .link(element.select(source.getUriTag()).attr("abs:href"))
            .preview(preview)
            .description(element.select(source.getDescriptionTag()).text())
            .pubDate(pubDate)
            .source(source)
            .build();
  }

}
