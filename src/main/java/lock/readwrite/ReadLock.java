package lock.readwrite;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();


        new Thread(()->{
            readLock.lock();
            try{
                log.debug("读锁进来了");
                int i=(1 << 16) - 1;
                int a=65536 & i;
                log.debug(i+"------++"+a);
            }finally {
                readLock.unlock();
            }
        },"t1").start();
    }
}
