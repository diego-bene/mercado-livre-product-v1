package br.com.mercadolivre.repository;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadolivre.model.Product;

@Component
public class ProductRepository {
	
	private final ObjectMapper mapper;
	
	@Value("${path.arquivos-prototipo}")
	private String FILE_PATH;
	
	@Value("${product.file-name}")
	private String FILE_NAME;

    public ProductRepository() {
        mapper = new ObjectMapper();
    }

	public void processPurchase(List<Product> listProduct) throws Exception {
    	
    	for(Product product: listProduct) {
    		// Salvar arquivo atualizado
    		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH + FILE_NAME), product);
    		System.out.println("Compra processada. Estoque atualizado para: " + product.getAvailableStock());
    	}
    }
    
    public Product search(Long productId) throws Exception {
		
    	Product product = null;
		
        File jsonFile = new File(FILE_PATH + FILE_NAME);

        product = mapper.readValue(jsonFile, Product.class);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

        return product;
	}
    
}