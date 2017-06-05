package org.ogenvik.futures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import static org.ogenvik.futures.Util.println;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
public class G_ListenableFutureFutures {

    ListenableFuture<String> getStuff() {
        return Futures.immediateFuture("This is the future!");
    }

    @Test
    public void testFutureBetter() {
        ListenableFuture<String> future = getStuff();
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

    ListenableFuture<String> getExceptionalStuff() {
        return MoreExecutors.newDirectExecutorService().submit(() -> {
            throw new RuntimeException("There is no future!");
        });

    }

    @Test
    public void testFutureWithExceptions() {
        ListenableFuture<String> future = getExceptionalStuff();
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

    @Test
    public void testFutureTransformed() {
        ListenableFuture<String> future = getStuff();
        ListenableFuture<Integer> transformedFuture = Futures.transform(future, String::length);

        Futures.addCallback(transformedFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                println(t);
            }
        });
    }

}
