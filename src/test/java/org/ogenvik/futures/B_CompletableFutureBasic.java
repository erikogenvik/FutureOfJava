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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
    public void testFutureApply() throws InterruptedException {
        CompletableFuture<String> future = getStuff();
        CompletableFuture<Integer> future2 = future.thenApply(String::length);
        future2.thenAccept(Util::println);
        Util.println("wee");

        Thread.sleep(2000);
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
