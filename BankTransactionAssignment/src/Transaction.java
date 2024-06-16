import java.math.BigDecimal;

public record Transaction(BigDecimal timestamp, String type, BigDecimal amount, BigDecimal currentReserve, Integer nDeclined) {
}
