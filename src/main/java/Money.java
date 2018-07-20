import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public double toDouble() {
        return this.amount.doubleValue();
    }
}
