package clthread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ClQueue {
    Deque<Runnable> deque=new ArrayDeque<>();
    //定义队列的最大存放大小
    int queueSize;

    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    Condition emptyCondition=lock.newCondition();
    public ClQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    public void put(Runnable runnable){
        try{
            lock.lock();
            while (deque.size()==queueSize){
                log.debug("队列中满了，在进来就要进行阻塞");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("队列中没满，将线程加到队列中");

            deque.addLast(runnable);
        }finally {
            lock.unlock();
        }

    }

    public Runnable poll(){
        try{
            lock.lock();
            if(deque.isEmpty()){
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            emptyCondition.signal();
            Runnable runnable = deque.removeFirst();
            return runnable;
        }finally {
            lock.unlock();
        }

    }
}
