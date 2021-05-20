package lock.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class AqsTest {

    public static void main(String[] args) throws InterruptedException {
        MyLock lock=new MyLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("1111");
            lock.unlock();
        });
        t1.start();

        lock.lock();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();

        log.info("2222"+t1.isInterrupted());
        lock.unlock();




    }
}
