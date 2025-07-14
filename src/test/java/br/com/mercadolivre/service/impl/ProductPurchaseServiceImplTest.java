package br.com.mercadolivre.service.impl;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.model.PurchaseStatus;
import br.com.mercadolivre.repository.ProductPurchaseRepository;
import br.com.mercadolivre.service.ProductDetailService;

//@SpringBootTest
public class ProductPurchaseServiceImplTest {

    @InjectMocks
    private ProductPurchaseServiceImpl productPurchaseService;

    @Mock
    private ProductDetailService productDetailService;

    @Mock
    private ProductPurchaseRepository productPurchaseRepository;

    private ProductPurchase purchase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        purchase = new ProductPurchase();
    }

    @Test
    public void testSavePurchase() throws Exception {
        // Act
        productPurchaseService.save(purchase);

        // Assert
        verify(productDetailService).processByPurchase(purchase);
        verify(productPurchaseRepository).savePurchase(purchase);

        assert purchase.getId() != null;
        assert purchase.getStatus() == PurchaseStatus.CREATED;
        assert purchase.getCreateDate() != null;
    }
}