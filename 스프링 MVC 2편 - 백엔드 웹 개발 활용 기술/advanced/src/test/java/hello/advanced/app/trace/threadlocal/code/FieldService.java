package hello.advanced.app.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String nameStore;

    public String logic(String name) {
        log.info("์ ์ฅ name={} -> nameStore={}", name, nameStore);
        nameStore = name;
        log.info("์กฐํ namestore={}", nameStore);
        sleep(1000);
        return nameStore;
    }

    private void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (Exception e) {

        }
    }
}
