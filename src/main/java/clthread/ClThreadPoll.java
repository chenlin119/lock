package clthread;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

/**
 * @author chenlin
 * 模拟线程池
 * 用于存储线程，线程提交
 */
@Slf4j
public class ClThreadPoll {
    //用于存放线程的集合
    private HashSet<ClNode> set;
    //处理线程数量
    private int core;

    private ClQueue clQueue;

    public ClThreadPoll(int size) {
        set = new HashSet<>();
        this.core = size;
        clQueue = new ClQueue(2);
    }

    public ClQueue getClQueue() {
        return clQueue;
    }

    public void submit(Runnable runnable) {

        if (set.size() < core) {
            log.debug("线程池里线程未满，可以继续执行 new一个线程");
            ClNode clNode = new ClNode(runnable, "Thread" + set.size() + 1,this);
            set.add(clNode);
            clNode.start();
        } else {
            log.debug("线程池满了，在进来线程需要到队列中排队等待,所以需要一个队列");
            clQueue.put(runnable);
        }
    }
}
