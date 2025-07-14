package br.com.mercadolivre.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.model.ProductPurchaseItem;
import br.com.mercadolivre.model.dto.ProductDetailDto;
import br.com.mercadolivre.repository.ProductRepository;

public class ProductDetailServiceImpTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductDetailServiceImpl productService;

    public ProductDetailServiceImpTest () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessByPurchase_Success() throws Exception {
        // Arrange
        ProductPurchaseItem item = new ProductPurchaseItem();
        item.setProductId(1L);
        item.setQuantity(2);
        item.setUnitPrice(100.0);
        item.setTotalPrice(200.0);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);
        product.setAvailableStock(5);

        when(productRepository.search(1L)).thenReturn(product);

        ProductPurchase purchase = new ProductPurchase();
        purchase.setItems(Arrays.asList(item));

        productService.processByPurchase(purchase);

        assertEquals(200.0, purchase.getTotalAmount());
        assertEquals(3, product.getAvailableStock());
    }

    @Test
    void testSearchDetail_ProductExists() throws Exception {

        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setProductTitle("Notebook Gamer");

        when(productRepository.search(productId)).thenReturn(product);

        ProductDetailDto expectedDto = new ProductDetailDto();
        expectedDto.setId(productId);
        expectedDto.setProductTitle("Notebook Gamer");

        ProductDetailServiceImpl spyService = spy(productService);

        ProductDetailDto result = spyService.searchDetail(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Notebook Gamer", result.getProductTitle());
    }

}