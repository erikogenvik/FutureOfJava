package org.ogenvik.futures;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.ogenvik.futures.Util.println;

/**
 * Created by erik on 2017-05-31.
 */
public class E_CompletableFutureHelper {

    CompletableFuture<String> getStuff() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            future.complete("This is the future!");
        }).start();

        return future;
    }

    @Test
    public void testRun() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 100; ++i) {
                //call something over the network
            }

            println("Completed our thing");
        });
    }

    @Test
    public void testSupply() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100; ++i) {
                //call something over the network
            }

            println("Completed our thing");
            return "A string we supply";

        });

        future.thenAccept(Util::println).join();
    }
    @Test
    public void testSupplyWithDelay() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100; ++i) {
                //call something over the network
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            println("Completed our thing");
            return "A string we supply";

        });

        future.thenAccept(Util::println).join();
    }
}
