package za.co.digitalplatoon.invoiceservice.invoice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class NestedError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}