package za.co.digitalplatoon.invoiceservice.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItemRequest {

    @NotNull(message = "Please provide the quantity")
    @Min(value = 1, message = "Quantity needs to be at least 1 for each item")
    private Long quantity;

    @NotNull(message = "Please enter a description of the item")
    @Size(min = 1, max = 250, message = "Description should be less than 250 characters")
    private String description;

    @NotNull(message = "Please enter the unit price of the item")
    @Positive
    private BigDecimal unitPrice;
}
