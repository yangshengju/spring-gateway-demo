package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class DemoController {

    @RequestMapping("/handle")
    public ResponseEntity<String> handle() {
        return null;
    }

    @GetMapping("/1")
    private String get1() {
        log.info("get1 start");
        String result = createStr();
        log.info("get1 end");
        return result;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }

    @GetMapping("/webflux/1")
    private Mono<String> getWebflux1() {
        log.info("getWebflux1 start");
        Mono<String> result = Mono.fromSupplier(()->createStr());
        log.info("getWebflux1 end");
        return result;
    }
}
