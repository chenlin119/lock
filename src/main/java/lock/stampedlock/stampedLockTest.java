package lock.stampedlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class stampedLockTest {
    StampedLock lock=new StampedLock();
    long writeL=0;
    static int i=0;
    public static void main(String[] args) throws InterruptedException {
        stampedLockTest s=new stampedLockTest();

        new Thread(()->{
            try {
                int result=s.read();
                log.debug("------"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                s.write(9);
            } finally {

            }
        }).start();






    }
    public int read() throws InterruptedException {
        long l = lock.tryOptimisticRead();
        log.debug("读进来"+l);
        TimeUnit.SECONDS.sleep(4);

        if(lock.validate(l)){
            log.debug("还未加锁，读取数据成功"+l);
            return i;
        }
        try {
            l = lock.readLock();
            log.debug("直接读取失败，进行读操作加锁"+l);
            return i;
        }finally {
            lock.unlockRead(l);
        }
    }
    public int write(int i){
        try {
            writeL=lock.writeLock();
            log.debug("写加锁"+writeL);
            this.i=i;
            return this.i;
        }finally {
            lock.unlockWrite(writeL);
        }

    }
}
