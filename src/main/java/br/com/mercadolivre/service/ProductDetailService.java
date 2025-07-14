package br.com.mercadolivre.service;

import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.model.dto.ProductDetailDto;

public interface ProductDetailService {
	ProductDetailDto searchDetail(Long productId) throws Exception;
	void processByPurchase(ProductPurchase purchase) throws Exception;
}