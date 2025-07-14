package br.com.mercadolivre.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.model.PurchaseStatus;
import br.com.mercadolivre.repository.ProductPurchaseRepository;
import br.com.mercadolivre.service.ProductDetailService;
import br.com.mercadolivre.service.ProductPurchaseService;

@Service
public class ProductPurchaseServiceImpl implements ProductPurchaseService {
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@Autowired
	private ProductPurchaseRepository productPurchaseManager;
    
	@Override
    public void save(ProductPurchase purchase) throws Exception {
    	
    	productDetailService.processByPurchase(purchase);
    	
    	UUID uuid = UUID.randomUUID();
    	purchase.setId(uuid.toString());
    	
    	purchase.setStatus(PurchaseStatus.CREATED);
    	purchase.setCreateDate(new Date());
    	
    	productPurchaseManager.savePurchase(purchase);
    }
}