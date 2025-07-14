package br.com.mercadolivre.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.mercadolivre.model.ProductPurchase;

@Component
public class ProductPurchaseRepository {
	
	@Value("${path.arquivos-prototipo}")
	private String FILE_PATH;
	
	@Value("${product-purchase.file-name}")
	private String FILE_NAME;
    
    private final ObjectMapper mapper;

    public ProductPurchaseRepository() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void savePurchase(ProductPurchase newPurchase) throws Exception {
    	
        List<ProductPurchase> purchases = readAllPurchases();
        purchases.add(newPurchase);
        mapper.writeValue(new File(FILE_PATH + FILE_NAME), purchases);
        System.out.println("Compra salva com sucesso!");
    }

    private List<ProductPurchase> readAllPurchases() {
        try {
            File file = new File(FILE_PATH + FILE_NAME);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<ProductPurchase>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}