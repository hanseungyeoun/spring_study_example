package hello.itemservice.web.validation.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class ItemSaveForm {
    @NotBlank
    private String itemName;

    @NotBlank
    @Range(min = 1000, max = 10000)
    private Integer price;
    private Integer quantity;
}
