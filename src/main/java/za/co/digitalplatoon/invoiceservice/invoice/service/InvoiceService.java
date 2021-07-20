package za.co.digitalplatoon.invoiceservice.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import za.co.digitalplatoon.invoiceservice.invoice.dao.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceRequest;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceResponse;
import za.co.digitalplatoon.invoiceservice.invoice.dto.LineItemRequest;
import za.co.digitalplatoon.invoiceservice.invoice.dto.LineItemResponse;
import za.co.digitalplatoon.invoiceservice.invoice.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.entity.LineItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceService {

    @Value("${global.vat.rate}")
    private Long vatRate;

    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository invoiceRepository;
    private static final int DECIMAL_SCALE = 2;
    private static final RoundingMode ROUND_MODE = RoundingMode.HALF_UP;

    @Autowired
    public InvoiceService(InvoiceRepository repository) {
        this.invoiceRepository = repository;
    }

    public InvoiceResponse saveInvoice(InvoiceRequest invoice) {
        Invoice objectToSave = mapRequestToObject(invoice);
        calculateTotalsAndUpdateInvoice(invoice, objectToSave);
        invoiceRepository.save(objectToSave);
        return buildResponseObject(objectToSave);
    }

    public InvoiceResponse retrieveInvoice(Long id) {
        Optional<Invoice> result = invoiceRepository.findById(id);
        return result.map(this::buildResponseObject).orElse(null);
    }

    public List<InvoiceResponse> retrieveAllInvoices() {
        log.info("Retrieving all invoices");
        List<InvoiceResponse> invoiceResponses = buildResponseFromList(invoiceRepository.findAll());
        log.info("{} records found", invoiceResponses.size());
        return invoiceResponses;
    }

    private void calculateTotalsAndUpdateInvoice(InvoiceRequest request, Invoice objectToSave) {
        BigDecimal invoiceSubTotal = calculateAndUpdateLineItemTotals(request.getLineItems(), objectToSave);
        objectToSave.setSubTotal(invoiceSubTotal);
        objectToSave.setVatRate(vatRate);
        BigDecimal vatTotal = invoiceSubTotal.multiply(BigDecimal.valueOf(vatRate, DECIMAL_SCALE));
        objectToSave.setVat(vatTotal.setScale(DECIMAL_SCALE, ROUND_MODE));
        objectToSave.setTotal(invoiceSubTotal.add(vatTotal).setScale(DECIMAL_SCALE, ROUND_MODE));
    }

    private BigDecimal calculateAndUpdateLineItemTotals(List<LineItemRequest> items, Invoice objectToSave) {
        BigDecimal invoiceSubTotal = BigDecimal.ZERO;
        for (LineItemRequest i : items) {
            LineItem item = new LineItem();
            item.setQuantity(i.getQuantity());
            item.setUnitPrice(i.getUnitPrice());
            item.setDescription(i.getDescription());
            item.setLineItemTotal(i.getUnitPrice()
                    .multiply(BigDecimal.valueOf(i.getQuantity()))
                    .setScale(DECIMAL_SCALE, ROUND_MODE));
            objectToSave.getLineItems().add(item);

            invoiceSubTotal = invoiceSubTotal.add(item.getLineItemTotal())
                    .setScale(DECIMAL_SCALE, ROUND_MODE);
        }

        return invoiceSubTotal;
    }

    private Invoice mapRequestToObject(InvoiceRequest request) {
        Invoice newRecord = new Invoice();
        newRecord.setClient(request.getClient());
        newRecord.setDate(request.getDate());
        return newRecord;
    }

    private List<InvoiceResponse> buildResponseFromList(List<Invoice> invoices) {
        List<InvoiceResponse> result = new ArrayList<>();
        invoices.forEach(r -> {
            result.add(buildResponseObject(r));
        });

        return result;
    }

    private InvoiceResponse buildResponseObject(Invoice record) {
        return new InvoiceResponse(record.getId(),
                buildLineItemResponses(record.getLineItems()),
                record.getClient(),
                record.getDate(),
                record.getVatRate(),
                record.getSubTotal(),
                record.getVat(),
                record.getTotal());
    }

    private List<LineItemResponse> buildLineItemResponses(List<LineItem> items) {
        List<LineItemResponse> result = new ArrayList<>();
        items.forEach(item -> {
            result.add(new LineItemResponse(item.getQuantity(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getLineItemTotal()));
        });
        return result;
    }
}
