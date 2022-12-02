package hello.advanced.app.v1;

import hello.advanced.app.trace.HelloTrace.HelloTraceV1;
import hello.advanced.app.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String item) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV1.request");
            orderRepository.save(item);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
