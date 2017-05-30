import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created by erik on 2017-05-30.
 */
public class B_CompletableFutureTest {



    CompletableFuture<String> getStuff() {
        CompletableFuture<String> future  = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                future.complete("This is the future!");
            } catch (InterruptedException ignored) {
            }
        }).run();

        return future;
    }

    CompletableFuture<String> getExceptionalStuff() {
        CompletableFuture<String> future  = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                future.completeExceptionally(new RuntimeException("There is no future!"));
            } catch (InterruptedException ignored) {
            }
        }).run();

        return future;
    }

    @Test
    public void testFuture(){
        CompletableFuture<String> future = getStuff();
        future.thenAccept(System.out::println);
    }

    @Test
    public void testFutureWithExceptions(){
        CompletableFuture<String> future = getExceptionalStuff();
        future.thenAccept(System.out::println).exceptionally(throwable -> {
            System.err.println(throwable.toString());
            return null;
        });
    }
}
