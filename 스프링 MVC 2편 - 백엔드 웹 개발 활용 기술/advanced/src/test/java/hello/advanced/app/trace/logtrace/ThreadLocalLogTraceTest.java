package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }
}
