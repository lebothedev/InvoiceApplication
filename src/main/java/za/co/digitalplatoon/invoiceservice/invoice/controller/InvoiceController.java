package za.co.digitalplatoon.invoiceservice.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceRequest;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceResponse;
import za.co.digitalplatoon.invoiceservice.invoice.service.InvoiceService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.invoiceService = service;
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        List<InvoiceResponse> allInvoices = invoiceService.retrieveAllInvoices();
        if (allInvoices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(allInvoices);
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> getInvoice(@PathVariable Long id) {
        InvoiceResponse result = invoiceService.retrieveInvoice(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/invoices", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addInvoice(@Valid @RequestBody InvoiceRequest invoice) {
        InvoiceResponse response = invoiceService.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
