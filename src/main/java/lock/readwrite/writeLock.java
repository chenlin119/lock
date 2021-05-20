package lock.readwrite;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class writeLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();



        new Thread(()->{
            writeLock.lock();
            try{
                log.debug("写锁进来了");
                int i=(1 << 16) - 1;
                int a=65536 & i;
                log.debug(i+"------++"+a);
            }finally {
                writeLock.unlock();
            }
        },"t1").start();
    }
}
