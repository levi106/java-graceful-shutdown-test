package com.example.gracefulshutdowntest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("health")
public class HealhthController {
    private static Boolean isHealth = true;
    @RequestMapping(value="/{healthy}", method=RequestMethod.PUT)
    public Integer put(@PathVariable() Integer healthy) {
        log.info("/health/{}", healthy);
        if (healthy == 1) {
            isHealth = true;
        } else if (healthy == 0) {
            isHealth = false;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameter");
        }
        return healthy;
    }

    @RequestMapping()
    public String get() {
        if (isHealth) {
            log.info("/health: Ok");
            return "Ok";
        } else {
            log.info("/health: Down");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Down");
        }
    }
}
