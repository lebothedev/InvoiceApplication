package za.co.digitalplatoon.invoiceservice.invoice;

import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceRequest;
import za.co.digitalplatoon.invoiceservice.invoice.dto.LineItemRequest;
import za.co.digitalplatoon.invoiceservice.invoice.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDataObjectHelper {

    public static Invoice getTestInvoice() {
        Invoice invoice = new Invoice();
        invoice.setId(123L);
        return invoice;
    }


    public static InvoiceRequest getTestInvoiceRequest() {
        InvoiceRequest request = new InvoiceRequest();
        request.setClient("Client_ABC");
        request.setDate(LocalDate.now());
        request.setLineItems(getTestLineItems());
        return request;
    }

    public static List<LineItemRequest> getTestLineItems() {
        List<LineItemRequest> result = new ArrayList<>();
        result.add(new LineItemRequest(2L, "Bread", BigDecimal.valueOf(10.99)));
        result.add(new LineItemRequest(5L, "Milk", BigDecimal.valueOf(74.99)));
        result.add(new LineItemRequest(1L, "Eggs", BigDecimal.valueOf(64.99)));
        return result;
    }
}
