package lock.guarded;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Slf4j(topic = "lock")
public class Test {
    public static void main(String[] args) {

        GuardedObject guardedObject = new GuardedObject();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        Thread t1=new Thread(() -> {
            String result = Operate.dbOprate1();
            log.debug("t1 set完毕...");
            guardedObject.setResponse(result);
        },"t1");

        t1.start();

        new Thread(() -> {
            String result = Operate.dbOprate();
            log.debug("t2 set完毕...");
            guardedObject.setResponse(result);
        },"t1").start();

        log.debug("主线程等待（wait）t1 set");
        //有没有实现超时？


        //guardedObject.setResponse(null);
        Object response = guardedObject.getResponse();

        log.debug("response: [{}]",response);
    }



}
