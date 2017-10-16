package nl.payconiq.request;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This VO represents the allowed properties to update a Stock and the validator rules
 * @author Rafael Del Sole
 */
public class PutStockVO {

    @NotNull
    @Min(value = 0)
    private Integer id;

    @NotNull
    @Min(value = 0)
    private BigDecimal currentPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
