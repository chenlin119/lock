package clthread;

public class ClNode extends Thread {
    private Runnable runnable;
    ClQueue clQueue;

    public ClNode(Runnable runnable,String threadName,ClThreadPoll clThreadPoll) {
        //给线程添加一个名称，调用父类方法
        setName(threadName);
        this.runnable=runnable;
        clQueue=clThreadPoll.getClQueue();
    }


    @Override
    public void run() {
        while(runnable!=null||(runnable=clQueue.poll())!=null){
            runnable.run();
            runnable=null;
        }


    }
}
