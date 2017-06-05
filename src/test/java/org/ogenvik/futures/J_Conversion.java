package org.ogenvik.futures;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Copyright © 2017 Erik Ogenvik.
 */
public class J_Conversion {

    static <T> CompletableFuture<T> wrap(final ListenableFuture<T> listenableFuture) {
        CompletableFuture<T> future = new CompletableFuture<T>();

        Futures.addCallback(listenableFuture, new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {
                future.complete(result);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }

    @Test
    public void testListenableToCompletable() {

        ListenableFuture<String> listenableFuture = MoreExecutors.listeningDecorator(ForkJoinPool.commonPool()).submit(() -> "This is the future!");
        CompletableFuture<String> completableFuture = wrap(listenableFuture);
        completableFuture.thenAccept(Util::println).join();
    }


    private <T> ListenableFuture<T> wrap(CompletableFuture<T> completableFuture) {

        class Wrapper implements ListenableFuture<T> {

            @Override
            public void addListener(Runnable listener, Executor executor) {
                completableFuture.thenAcceptAsync(t -> listener.run(), executor);
            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return completableFuture.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return completableFuture.isCancelled();
            }

            @Override
            public boolean isDone() {
                return completableFuture.isDone();
            }

            @Override
            public T get() throws InterruptedException, ExecutionException {
                return completableFuture.get();
            }

            @Override
            public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return completableFuture.get(timeout, unit);
            }
        }
        return new Wrapper();
    }

    @Test
    public void testCompletableToListenable() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "This is the future!");

        ListenableFuture<String> listenableFuture = wrap(completableFuture);
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Util.println(result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
