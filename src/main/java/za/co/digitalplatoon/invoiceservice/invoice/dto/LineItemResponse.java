package za.co.digitalplatoon.invoiceservice.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItemResponse {
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal lineItemTotal;
}
