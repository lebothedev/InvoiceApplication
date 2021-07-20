package za.co.digitalplatoon.invoiceservice.invoice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.digitalplatoon.invoiceservice.invoice.TestDataObjectHelper;
import za.co.digitalplatoon.invoiceservice.invoice.dao.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceResponse;
import za.co.digitalplatoon.invoiceservice.invoice.entity.Invoice;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository repository;

    @InjectMocks
    private InvoiceService invoiceService;

    @Before
    public void initFields() {
        ReflectionTestUtils.setField(invoiceService, "vatRate", 15L);
    }

    @Test
    public void testSaveInvoice_Successful() {
        InvoiceResponse response = invoiceService.saveInvoice(TestDataObjectHelper.getTestInvoiceRequest());
        verify(repository).save(Mockito.any());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getVat());
        Assert.assertNotNull(response.getSubTotal());
        Assert.assertNotNull(response.getTotal());
        Assert.assertNotNull(response.getVatRate());
    }

    @Test
    public void testRetrieveInvoice_Successful() {
        Invoice testInvoice = TestDataObjectHelper.getTestInvoice();
        when(repository.findById(anyLong())).thenReturn(Optional.of(testInvoice));
        InvoiceResponse response = invoiceService.retrieveInvoice(123L);

        verify(repository).findById(anyLong());
        verify(repository, times(0)).save(any());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getId());
    }

    @Test(expected = Exception.class)
    public void testSaveInvoice_Unsuccessful() {
        when(repository.save(any())).thenThrow(new Exception());
        invoiceService.saveInvoice(TestDataObjectHelper.getTestInvoiceRequest());
    }

}