package org.ogenvik.futures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
public class I_CompleteProblem {


    @Test
    public void testComplete() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.thenAccept(Util::println);
        future.complete("This is the future!");
        future.complete("Another future is possible!"); //Ignored
        future.join();
    }

    @Test
    public void testCompleteListenable() {
        ListenableFuture<String> future = Futures.immediateFuture("This is the future!");
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Util.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        //Can't complete externally

    }
}
