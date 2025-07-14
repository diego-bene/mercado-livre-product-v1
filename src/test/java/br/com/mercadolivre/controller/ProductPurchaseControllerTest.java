package br.com.mercadolivre.controller;

import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.service.ProductPurchaseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductPurchaseControllerTest {

    @InjectMocks
    private ProductPurchaseController controller;

    @Mock
    private ProductPurchaseService purchaseService;

    private ProductPurchase samplePurchase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        samplePurchase = new ProductPurchase(); // Preencha com valores reais se necessário
    }

    @Test
    public void testSave_Success() throws Exception {
        doNothing().when(purchaseService).save(samplePurchase);

        ResponseEntity<String> response = controller.save(samplePurchase);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Compra salva com sucesso!", response.getBody());
    }

    @Test
    public void testSave_IllegalStateException() throws Exception{
        doThrow(new IllegalStateException("Erro de validação")).when(purchaseService).save(samplePurchase);

        ResponseEntity<String> response = controller.save(samplePurchase);

        assertEquals(422, response.getStatusCodeValue());
        assertEquals("Erro de validação", response.getBody());
    }

    @Test
    public void testSave_GenericException() throws Exception{
        doThrow(new RuntimeException("Falha genérica")).when(purchaseService).save(samplePurchase);

        ResponseEntity<String> response = controller.save(samplePurchase);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Estamos com indisponibilidade no sistema, favor tentar novamente mais tarde!", response.getBody());
    }
}