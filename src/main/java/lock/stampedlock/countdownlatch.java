package lock.stampedlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class countdownlatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(3);

        ExecutorService executorService=Executors.newFixedThreadPool(3);
        for (int i = 0; i <3 ; i++) {
            executorService.submit(()->{
                try {
                    log.debug("------");
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.debug("main----in");
        countDownLatch.await();
        log.debug("main----out");

    }
}
