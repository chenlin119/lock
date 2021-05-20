package lock.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "lock")
public class TestTimeOut {
    public static void main(String[] args) throws InterruptedException {
        GuardedObjectTimeOut guardedObject = new GuardedObjectTimeOut();
        log.debug("xxxx");
        //4s执行完
        Thread thread=new Thread(() -> {
            String result = Operate.dbOprate();
            log.debug("t1 set完毕...");
            //guardedObject.setResponse(result);
        },"t1");


        thread.start();;
        thread.join();

        log.debug("主线程等待t1 set");

        //Object response = guardedObject.getResponse(2000);
        log.debug("response: [{}] ");
    }



}
