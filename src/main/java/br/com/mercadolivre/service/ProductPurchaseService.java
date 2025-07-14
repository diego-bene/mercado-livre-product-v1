package br.com.mercadolivre.service;

import br.com.mercadolivre.model.ProductPurchase;

public interface ProductPurchaseService {
    void save(ProductPurchase purchase) throws Exception;
}