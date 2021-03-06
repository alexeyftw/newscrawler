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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RssFeedProcessor {

  @Autowired
  private ItemRepository itemRepository;

  void processRssDocument(Document document, Source source) {
    Elements elements = document.select(source.getNewsContainerTag());
    Item lastItem = itemRepository.findFirstBySourceOrderByPubDateDesc(source);
    List<Item> items = elements.stream()
            .map(element -> buildItem(element, source))
            .filter(i -> !i.getTitle().isBlank())
            .filter(lastItem == null ? i -> true : i -> i.getPubDate().after(lastItem.getPubDate()))
            .collect(Collectors.toList());
    itemRepository.saveAll(items);
  }

  private Item buildItem(Element element, Source source) {

    String preview = PreviewCreatorUtil.createPreview(element, source);
    Date pubDate = DateTimeParserUtil.parseDate(element.select(source.getPubDateTag()).text(),
            source.getPubDateFormat());

    return Item.builder()
            .title(element.select(source.getTitleTag()).text())
            .link(element.select(source.getUriTag()).text())
            .preview(preview)
            .pubDate(pubDate)
            .description("")
            .source(source)
            .build();
  }

}
