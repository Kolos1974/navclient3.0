package program;

import javafx.concurrent.Task;

import java.util.concurrent.*;

public class ThreadPool implements Shutdownable {

    private final ExecutorService pool;

    public static final ThreadPool INSTANCE = new ThreadPool();

    private ThreadPool() {
        int POOLSIZE = 3;
        pool = Executors.newFixedThreadPool(POOLSIZE);
    }

    public void runTask(Task t) {
        pool.execute(t);
    }

    @Override
    public void shutdown() {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
