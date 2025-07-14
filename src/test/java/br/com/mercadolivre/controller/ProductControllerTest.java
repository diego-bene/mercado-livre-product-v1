package br.com.mercadolivre.controller;

import br.com.mercadolivre.model.dto.ProductDetailDto;
import br.com.mercadolivre.service.ProductDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDetailService productDetailService;

    @Test
    public void testGetProductDetail() throws Exception {
        // Arrange
        Long productId = 1L;
        ProductDetailDto mockDto = new ProductDetailDto();
        mockDto.setProductTitle("Produto Teste");
        mockDto.setProductDescription("Descrição do produto");
        mockDto.setPrice(99.90);

        Mockito.when(productDetailService.searchDetail(productId)).thenReturn(mockDto);

        // Act & Assert
        mockMvc.perform(get("/product_detail/{id}/search", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productTitle").value("Produto Teste"));
    }
}