package lock.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockTest11 {
    static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();

    public static void main(String[] args) {
        writeLock.newCondition();
        readLock.newCondition();
        new Thread(()->{
            writeLock.lock();
            try {
                log.info("进来了");
                readLock.lock();
                dd(10);
            }finally {
                readLock.unlock();
            }

        },"read1").start();
       /* new Thread(()->{
            readLock.lock();
            try {
                log.info("读进来了");
                dd(10);
            }finally {
                readLock.unlock();
            }

        },"read2").start();*/
        /*new Thread(()->{
            writeLock.lock();
            try{
                log.info("写进来了");
                dd(15);
            }finally {
                writeLock.unlock();
            }

        },"write1").start();*/

       /* new Thread(()->{
            writeLock.lock();
            try{
                log.info("写进来了");
                dd(15);
            }finally {
                writeLock.unlock();
            }

        },"write2").start();*/

    }

    static void dd(int num){
        for (int i = 0; i <num ; i++) {
            log.info("到底是哪个调用");
        }
    }
}
