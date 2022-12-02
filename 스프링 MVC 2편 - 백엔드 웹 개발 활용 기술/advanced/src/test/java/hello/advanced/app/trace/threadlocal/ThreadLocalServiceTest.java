package hello.advanced.app.trace.threadlocal;

import hello.advanced.app.trace.threadlocal.code.FieldService;
import hello.advanced.app.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService threadLocal = new ThreadLocalService();

    @Test
    public void test(){
        log.info("main start");
        Runnable userA = ()-> {
            threadLocal.logic("userA");
        };

        Runnable userB = ()-> {
            threadLocal.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("threat-A");

        Thread threadB = new Thread(userB);
        threadB.setName("threat-B");

        threadA.start();
        sleep(100); //동시성 문제 발생하지 않는 코드
        threadB.start();
    }

    private void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
