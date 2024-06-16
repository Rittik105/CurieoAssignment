import java.math.BigDecimal;

public record Command(String type, BigDecimal value1, BigDecimal value2) {
}
