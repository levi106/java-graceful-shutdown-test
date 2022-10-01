package com.example.gracefulshutdowntest.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("sleep")
public class SleepController {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyyMMdd_HHmmss");

    @RequestMapping("/{millis}")
    public Mono<String> get(@PathVariable Integer millis) {
        log.info("/sleep: {}", millis);
        final StopWatch sw = new StopWatch();
        log.info("Thread # {}: start {}", Thread.currentThread().getId(), DF.format(Date.from(Instant.now())));
        sw.start();
        return Mono.just(sw)
            .delayElement(Duration.ofMillis(millis))
            .log()
            .map(x -> {
                log.info("Thread # {}: map {}", Thread.currentThread().getId(), DF.format(Date.from(Instant.now())));
                x.stop();
                return Long.toString(x.getTotalTimeMillis());
            });
    }
}
