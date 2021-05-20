package lock.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class MyLock implements Lock {

    MyAqs myAqs=new MyAqs();
    @Override
    public void lock() {
        myAqs.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        myAqs.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return myAqs.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        myAqs.release(1);
    }

    @Override
    public Condition newCondition() {
        return myAqs.newCondition();
    }


}
