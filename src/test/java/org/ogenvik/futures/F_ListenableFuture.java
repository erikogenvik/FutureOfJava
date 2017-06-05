package org.ogenvik.futures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.ogenvik.futures.Util.println;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
public class F_ListenableFuture {
    ListenableFuture<String> getStuff() {
        return Futures.immediateFuture("This is the future!");
    }

    @Test
    public void testFuture() {
        ListenableFuture<String> future = getStuff();
        future.addListener(() -> {
            try {
                println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());
    }


}
