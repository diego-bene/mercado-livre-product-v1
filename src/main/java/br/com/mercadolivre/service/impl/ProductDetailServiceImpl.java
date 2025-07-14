package br.com.mercadolivre.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.model.ProductPurchaseItem;
import br.com.mercadolivre.model.dto.ProductDetailDto;
import br.com.mercadolivre.repository.ProductRepository;
import br.com.mercadolivre.service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	
	@Autowired
	private ProductRepository productRepository;
    
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Override
    public ProductDetailDto searchDetail(Long productId) throws Exception {
		
		ProductDetailDto productDetail = null;
		
		Product product = productRepository.search(productId);
		
		if(product != null) {
			productDetail = convertToDTO(product);
		}
		
        return productDetail;
    }
	
	@Override
    public void processByPurchase(ProductPurchase purchase) throws Exception {
    	
    	Double totalAmountProductPurchase =  0d;
    	
    	List<Product> listProduct = new ArrayList<>();
    	
    	for(ProductPurchaseItem productPurchase : purchase.getItems()) {
    		
    		Product product = productRepository.search(productPurchase.getProductId());
    		
    		if (!product.getId().equals(productPurchase.getProductId())) {
    			throw new IllegalArgumentException("Produto com ID " + productPurchase.getProductId() + " não encontrado.");
    		}
    		
    		Double totalAmount = productPurchase.getUnitPrice() * productPurchase.getQuantity();
    		if(!product.getPrice().equals(productPurchase.getUnitPrice()) || !totalAmount.equals(productPurchase.getTotalPrice())) {
    			throw new IllegalStateException("O valor foi alterado, favor atualizar a pagina:");
    		}
    		
    		// Verificar estoque
    		int newQuantityAvailableStock = product.getAvailableStock();
    		newQuantityAvailableStock = newQuantityAvailableStock - productPurchase.getQuantity();
    		if (newQuantityAvailableStock < 0) {
    			throw new IllegalStateException("Estoque insuficiente. Disponível: " + newQuantityAvailableStock);
    		}
    		
    		product.setAvailableStock(newQuantityAvailableStock);
    		listProduct.add(product);
    		totalAmountProductPurchase += totalAmount;
    	}
    	
    	productRepository.processPurchase(listProduct);
    	
    	purchase.setTotalAmount(totalAmountProductPurchase);
    }
	
    private static ProductDetailDto convertToDTO(Product product) {
        return modelMapper.map(product, ProductDetailDto.class);
    }

}
