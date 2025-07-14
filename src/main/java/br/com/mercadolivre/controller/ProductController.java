package br.com.mercadolivre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.model.dto.ProductDetailDto;
import br.com.mercadolivre.service.ProductDetailService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product_detail")
public class ProductController {
	
	@Autowired
	private ProductDetailService productDetailService;

    @GetMapping("/{id}/search")
    public ProductDetailDto getProductDetail(@PathVariable Long id) throws Exception {
        return productDetailService.searchDetail(id);
    }
}
