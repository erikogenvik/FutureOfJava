package org.ogenvik.futures;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * Created by erik on 2017-05-30.
 */
public class A_FutureTask {


    Future<String> getStuff() {
        FutureTask<String> future = new FutureTask<>(() -> "This is the future!");

        new Thread(() -> {
                future.run();
        }).start();
        return future;
    }

    @Test
    public void testFuture() {

        Future<String> future = getStuff();
        while (!future.isDone()) {
        }
        try {
            Util.println(future.get());
        } catch (InterruptedException e) {
            Util.println("We were interrupted. Even though the Future had completed. Yay...");
        } catch (ExecutionException e) {
            Util.println("The future had aborted. But we checked that it had completed with 'isDone()'. Hmmm...");
        }
    }

}
