package za.co.digitalplatoon.invoiceservice.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();

    private String client;
    private LocalDate date;
    private Long vatRate;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal total;
}
