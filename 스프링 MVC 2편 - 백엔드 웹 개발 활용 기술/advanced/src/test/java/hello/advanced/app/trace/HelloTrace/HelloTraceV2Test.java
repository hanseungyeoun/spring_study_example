package hello.advanced.app.trace.HelloTrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hell0");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_execption() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hell0");
        TraceStatus status2= trace.beginSync(status1.getTraceId(), "hell0");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}