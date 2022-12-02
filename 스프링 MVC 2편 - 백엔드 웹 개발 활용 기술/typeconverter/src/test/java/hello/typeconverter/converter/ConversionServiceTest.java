package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Default;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {
    @Test
    void conversionServic(){
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new StringToIpPortConverter());
        service.addConverter(new IntegerToStringConverter());
        service.addConverter(new StringToIpPortConverter());
        service.addConverter(new IpPortToStringConverter());


        Assertions.assertThat(service.convert("10", Integer.class)).isEqualTo(10);
        Assertions.assertThat(service.convert(20, String.class)).isEqualTo("20");
        Assertions.assertThat(service.convert("127.0.0.1:8080", IpPort.class)).
                isEqualTo(new IpPort("127.0.0.1", 8080));
        Assertions.assertThat(service.convert(new IpPort("127.0.0.1", 8080), String.class)).
                isEqualTo("127.0.0.1:8080");

    }
}
