package hello.advanced.app.trace.HelloTrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hell0");
        trace.end(status);
    }

    @Test
    void begin_execption() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hell0");
        trace.exception(status, new IllegalStateException());
    }

}