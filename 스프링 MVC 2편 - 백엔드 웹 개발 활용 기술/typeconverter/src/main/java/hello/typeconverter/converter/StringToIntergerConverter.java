package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntergerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("covert source={}", source);
        return Integer.valueOf(source);
    }
}
