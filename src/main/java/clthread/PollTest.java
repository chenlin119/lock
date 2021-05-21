package clthread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenlin
 */
@Slf4j
public class PollTest {

    public static void main(String[] args) {
        ClThreadPoll clThreadPoll = new ClThreadPoll(2);
        for (int i = 0; i < 5; i++) {
            clThreadPoll.submit(() -> {
                log.debug("-----");

            });
        }

    }
}
