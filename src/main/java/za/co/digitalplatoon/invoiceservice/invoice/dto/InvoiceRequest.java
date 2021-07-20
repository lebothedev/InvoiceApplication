package za.co.digitalplatoon.invoiceservice.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @NotNull(message = "Please enter the client name")
    private String client;

    @NotNull(message = "Please enter a valid date. Format('yyyy-MM-dd'")
    private LocalDate date;

    @NotNull(message = "Please provide the line items for the invoice. Minimum of 1 required")
    @NotEmpty(message = "Please provide the line items for the invoice. Minimum of 1 required")
    private List<@Valid LineItemRequest> lineItems;
}
