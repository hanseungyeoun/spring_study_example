package hello.advanced.app.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore);
        nameStore.set(name);
        log.info("조회 namestore={}", nameStore.get());
        sleep(1000);
        return nameStore.get();
    }

    private void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (Exception e) {

        }
    }
}
