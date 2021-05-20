package lock.stampedlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class samephore {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(4);
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    log.debug("start");
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
