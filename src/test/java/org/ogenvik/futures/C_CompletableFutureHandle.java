package org.ogenvik.futures;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.ogenvik.futures.Util.println;


/**
 * Created by erik on 2017-05-30.
 */
public class C_CompletableFutureHandle {


    CompletableFuture<String> getStuff() {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            future.complete("This is the future!");
        }).start();

        return future;
    }

    @Test
    public void testFutureWhenComplete() {
        CompletableFuture<String> future = getStuff();
        future.whenComplete((s, throwable) -> {
            if (throwable == null) {
                println(s);
            } else {
                //Do some exception handling
            }
        });
    }

    @Test
    public void testFutureHandle() {
        CompletableFuture<String> future = getStuff();
        future.handle((s, throwable) -> {
            if (throwable == null) {
                return s.length();
            } else {
                //Do some exception handling
                return 0;
            }
        }).thenAccept(Util::println);
    }

}
