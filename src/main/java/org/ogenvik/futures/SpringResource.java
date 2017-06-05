package org.ogenvik.futures;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
@Controller
@RequestMapping("/spring")
public class SpringResource {


    @RequestMapping("sync")
    ResponseEntity sync() throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok("spring sync");
    }

    @RequestMapping("async")
    @Async
    CompletableFuture<ResponseEntity> async() {
        return FuturesApplication.completeLater()
                .thenApply(s -> ResponseEntity.ok("spring " + s));
    }
}
