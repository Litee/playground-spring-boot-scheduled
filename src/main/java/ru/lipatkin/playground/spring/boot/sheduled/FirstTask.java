package ru.lipatkin.playground.spring.boot.sheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class FirstTask {
    private static final Logger log = LoggerFactory.getLogger(FirstTask.class);
    private static final java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Scheduled(fixedRate = 1000)
    @Async
    public void reportCurrentTime() {
        log.info("First task - time is {}", formatter.format(java.time.LocalDateTime.now()));
    }
}
