package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieildLogTraceTest {
    FieildLogTrace trace = new FieildLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }
}