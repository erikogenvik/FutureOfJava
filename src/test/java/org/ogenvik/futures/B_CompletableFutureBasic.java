package org.ogenvik.futures;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;


/**
 * Created by erik on 2017-05-30.
 */
public class B_CompletableFutureBasic {


    CompletableFuture<String> getStuff() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
                future.complete("This is the future!");
        }).start();

        return future;
    }

    @Test
    public void testFutureAccept() {
        CompletableFuture<String> future = getStuff();
        future.thenAccept(Util::println).join();
    }

    @Test
    public void testFutureApply() {
        CompletableFuture<String> future = getStuff();
        future.thenApply(String::length).thenAccept(Util::println).join();
    }

    CompletableFuture<String> getExceptionalStuff() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
                future.completeExceptionally(new RuntimeException("There is no future!"));
        }).start();

        return future;
    }

    @Test
    public void testFutureWithExceptions() {
        CompletableFuture<String> future = getExceptionalStuff();
        future.exceptionally(Util::println).join();
    }
}
