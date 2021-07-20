package za.co.digitalplatoon.invoiceservice.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {

    private Long id;
    private List<LineItemResponse> lineItems = new ArrayList<>();
    private String client;
    private LocalDate date;
    private Long vatRate;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal total;
}
