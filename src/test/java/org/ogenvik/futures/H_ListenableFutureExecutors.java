package org.ogenvik.futures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.ogenvik.futures.Util.println;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
public class H_ListenableFutureExecutors {


    ListenableFuture<String> getStuffDirectly() {
        return MoreExecutors.newDirectExecutorService().submit(() -> "This is the future!");
    }


    @Test
    public void testFutureDirectly() {
        ListenableFuture<String> future = getStuffDirectly();
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                println(t);
            }
        });
    }


    ListenableFuture<String> getStuffInPool() {
        return MoreExecutors.listeningDecorator(ForkJoinPool.commonPool()).submit(() -> "This is the future!");
    }

    @Test
    public void testFutureInPool() {
        ListenableFuture<String> future = getStuffInPool();
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                println(t);
            }
        });
    }


}
