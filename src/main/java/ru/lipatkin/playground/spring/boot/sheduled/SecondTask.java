package ru.lipatkin.playground.spring.boot.sheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class SecondTask {
    private static final Logger log = LoggerFactory.getLogger(SecondTask.class);
    private static final java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private TaskExecutor executor;

    @Autowired
    private FirstTask firstTask;

    @Scheduled(fixedRate = 1000)
    @Async
    public void reportCurrentTime() {
        log.info("Second task - time is {}", formatter.format(java.time.LocalDateTime.now()));
        if (Math.random() < 0.5) {
            log.info("Firing first task programmatically");
            executor.execute(() -> firstTask.reportCurrentTime());
        }
    }
}
