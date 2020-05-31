package com.example.newscrawler.task;

import com.example.newscrawler.service.NewsCrawlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FindNewsTask {

    @Autowired
    private NewsCrawlService newsCrawlService;

    private Logger logger = LoggerFactory.getLogger(FindNewsTask.class);

    @Scheduled(fixedRate = 1000 * 60 * 5)
    public void scheduleFixedRateTaskAsync() {
        logger.info("Refreshing news has started");
        newsCrawlService.findNews();
    }

}