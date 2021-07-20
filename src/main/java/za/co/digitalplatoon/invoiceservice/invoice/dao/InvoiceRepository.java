package za.co.digitalplatoon.invoiceservice.invoice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.digitalplatoon.invoiceservice.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
