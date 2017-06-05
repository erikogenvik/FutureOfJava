package org.ogenvik.futures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class FuturesApplication {
    private static ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    static {
        taskScheduler.initialize();
    }

    public static void main(String[] args) {
        SpringApplication.run(FuturesApplication.class, args);
    }

    public static CompletableFuture<String> completeLater() {
        CompletableFuture<String> future = new CompletableFuture<>();
        taskScheduler.schedule(() -> future.complete("async"), Date.from(Instant.now().plusSeconds(1)));
        return future;
    }
}