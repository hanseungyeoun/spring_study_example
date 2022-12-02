package hello.itemservice.validation;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    public void messageCodesResolverObject(){
        String[] resolveMessageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String resolveMessageCode : resolveMessageCodes) {
            System.out.println("resolveMessageCode = " + resolveMessageCode);
        }

        assertThat(resolveMessageCodes).containsExactly("required.item.itemName", "required");
    }

    @Test
    public void messageCodesResolverField(){
        String[] resolveMessageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String resolveMessageCode : resolveMessageCodes) {
            System.out.println("resolveMessageCode = " + resolveMessageCode);
        }

       assertThat(resolveMessageCodes).containsExactly("required.item.itemName", "required.itemName",
               "required.java.lang.String", "required");
    }

}
