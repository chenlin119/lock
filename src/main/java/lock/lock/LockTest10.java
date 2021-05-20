package lock.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "enjoy")
public class LockTest10 {

    static int k=0;

    static final ReentrantLock lock=new ReentrantLock();
    static Condition condition=lock.newCondition();
    public static void main(String[] args) {
        waitNofity waitNofity = new waitNofity();

        new Thread(() -> {
            try {
                waitNofity.print("a",1,2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();


        new Thread(() -> {
            try {
                waitNofity.print("b",2,3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();




        new Thread(() -> {
            try {
                waitNofity.print("c",3,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();



    }
    //et t2  t3
    //

    static class waitNofity{

        int flag=1;

        public void print(String content,int waitFlag,int nextFlag) throws InterruptedException {
            for (int i = 0; i <4 ; i++) {
                Thread.sleep(800);
                try{
                    lock.lock();
                    while (flag!= waitFlag){
                        condition.await();
                    }
                    System.out.print(content);

                    flag= nextFlag;
                    condition.signal();

                }finally {
                    lock.unlock();
                }




            }

        }
    }
}
