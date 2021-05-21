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
            log.debug("队列长度put-----"+deque.size());
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
            emptyCondition.signal();
        }finally {
            lock.unlock();
        }

    }

    public Runnable poll(){
        try{
            lock.lock();

            log.debug("队列长度poll----"+deque.size()+"---"+deque.isEmpty());
            if(deque.isEmpty()){
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            log.debug("从队列中取出一个线程处理");


            Runnable runnable = deque.removeFirst();
            condition.signal();
            return runnable;
        }finally {
            lock.unlock();
        }

    }
}
