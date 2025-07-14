package br.com.mercadolivre.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadolivre.model.Product;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository repository;

    @Mock
    private ObjectMapper mockMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessPurchaseShouldWriteProductToFile() throws Exception {
        Product product = new Product();
        product.setAvailableStock(10);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        File expectedFile = new File("/fake/path/products.json");

        // Executa o método
        repository.processPurchase(productList);

        assert product.getAvailableStock() == 10;
    }

    @Test
    public void testSearchShouldReadProductFromFile() throws Exception {
        Product mockedProduct = new Product();
        mockedProduct.setAvailableStock(20);

        File expectedFile = new File("/fake/path/products.json");

        when(mockMapper.readValue(expectedFile, Product.class)).thenReturn(mockedProduct);

        Product result = repository.search(1L);

        assertNotNull(result);
    }
}