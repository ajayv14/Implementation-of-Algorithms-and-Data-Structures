
// credits : www.caveofprogramming.com
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Chapter 4

/**
 * 
 * This class demonstrates the use of thread pools in Java using the
 * ExecutorService framework.
 * The class implements the Runnable interface, allowing instances to be
 * executed by threads. *
 */

class ThreadPool implements Runnable {

  private int threadId;

  public ThreadPool(int tId) {
    this.threadId = tId;
  }

  public void run() {

    System.out.println("started thread : " + threadId);

    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("close thread : " + threadId);
  }






  public static void main(String[] args) {

    /*
     * Tasks are executed in parallel, but limited to 2 concurrent executions due to the thread pool size
     */

    ExecutorService executor = Executors.newFixedThreadPool(2); // thread pool size

    for (int i = 0; i < 5; i++) {

        executor.submit(new ThreadPool(i)); // 5 tasks submitted

    }

    executor.shutdown();// wait and shut down after all tasks are complete

    System.out.println("All tasks are submitted");

    try {
      executor.awaitTermination(1, TimeUnit.DAYS); // wait till task execution completion // here it is 1 day
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("All tasks are now complete");

  }


  /**
   * Expected output : 
      All tasks are submitted
      started thread : 1
      started thread : 0
      close thread : 0
      close thread : 1
      started thread : 2
      started thread : 3
      close thread : 2
      close thread : 3
      started thread : 4
      close thread : 4
      All tasks are now complete
   */

}