package nl.payconiq.request;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This VO represents the allowed properties to create a Stock and the validator rules
 * @author Rafael Del Sole
 */
public class PostStockVO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private BigDecimal currentPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
