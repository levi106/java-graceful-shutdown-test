package com.example.gracefulshutdowntest.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("host")
public class HostController {
    @RequestMapping()
    public String get() {
        log.info("/host");
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            return ip.toString();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }
}
