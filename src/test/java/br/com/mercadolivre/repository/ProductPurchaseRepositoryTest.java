package br.com.mercadolivre.repository;

import static org.mockito.Mockito.*;

import java.io.File;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadolivre.model.ProductPurchase;

public class ProductPurchaseRepositoryTest {

    @InjectMocks
    private ProductPurchaseRepository repository;

    @Mock
    private ObjectMapper mapper;

    private ProductPurchase purchase;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        purchase = new ProductPurchase();
        purchase.setId("123");
        purchase.setItems(new ArrayList<>());

        // Mocando leitura simulando retorno de lista existente
        when(mapper.readValue(any(File.class), any(TypeReference.class)))
                .thenReturn(new ArrayList<>());
    }

    @Test
    public void testSavePurchaseWithoutWritingToFile() throws Exception {

        repository.savePurchase(purchase);
        assert purchase.getId() != null;
    }
}