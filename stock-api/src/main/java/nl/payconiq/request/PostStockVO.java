package nl.payconiq.request;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PostStockVO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
