package com.tag.midnight.club.server.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJob {

    @Scheduled(cron = "0 * * * * *")
    public void run() {
        log.info("test service running");
    }
}
