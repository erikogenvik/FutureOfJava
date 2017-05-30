import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by erik on 2017-05-30.
 */
public class A_FutureTaskTest {

    Future<String> getStuff() {
        FutureTask<String> future = new FutureTask<>(() -> "This is the future!");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                future.run();
            } catch (InterruptedException ignored) {
            }
        }).run();
        return future;
    }

    @Test
    public void testFuture() throws InterruptedException, ExecutionException {

        Future<String> future = getStuff();
        while (!future.isDone()) {
            Thread.sleep(100);
        }
        System.out.println(future.get());
    }

    @Test
    public void testFutureCorrectly() {

        Future<String> future = getStuff();
        while (!future.isDone()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            System.out.println("We were interrupted. Even though the Future had completed. Yay...");
        } catch (ExecutionException e) {
            System.out.println("The future had aborted. But we checked that it had completed with 'isDone()'. Hmmm...");
        }
    }

}
