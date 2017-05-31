package org.ogenvik.futures;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.ogenvik.futures.Util.println;

/**
 * Created by erik on 2017-05-31.
 */
public class D_CompletableFutureAsync {

    CompletableFuture<String> getStuff() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            future.complete("This is the future!");
        }).start();

        return future;
    }

    @Test
    public void testLongChaining() {
        CompletableFuture<String> future = getStuff();
        future.thenApply(String::length)
                .thenApply(length -> length * length * length * length * length)
                .thenApply(Integer::toHexString)
                .thenApply(String::getBytes)
                .thenAccept(bytes -> {
                    for (byte b : bytes) {
                        println(b);
                    }
                });
    }

    @Test
    public void testLongChainingAsync() {
        CompletableFuture<String> future = getStuff();
        future.thenApplyAsync(String::length)
                .thenApplyAsync(length -> length * length * length * length * length)
                .thenApplyAsync(Integer::toHexString)
                .thenApplyAsync(String::getBytes)
                .thenAcceptAsync(bytes -> {
                    for (byte b : bytes) {
                        println(b);
                    }
                });
    }
}
