package hello.advanced.app.trace.threadlocal;

import hello.advanced.app.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    public void test(){
        log.info("main start");
        Runnable userA = ()-> {
            fieldService.logic("userA");
        };

        Runnable userB = ()-> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("threat-A");

        Thread threadB = new Thread(userB);
        threadB.setName("threat-B");

        threadA.start();
        sleep(3000); //동시성 문제 발생하지 않는 코드
        threadB.start();

        threadA.start();
        sleep(100); //동시성 문제 발생하는 코드
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
