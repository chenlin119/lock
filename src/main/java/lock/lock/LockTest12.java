package lock.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockTest12 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Thread t1 = new Thread(() -> {

            lock.lock();
            try {
                log.debug("t1 进来了");
                //TimeUnit.SECONDS.sleep(5);

            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();


        lock.lock();
        //TimeUnit.SECONDS.sleep(100000);
        log.debug("main 进来了");
        t1.interrupt();
        TimeUnit.SECONDS.sleep(100000);
        //


        lock.unlock();
    }
}
