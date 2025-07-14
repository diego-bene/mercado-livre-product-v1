package br.com.mercadolivre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.model.ProductPurchase;
import br.com.mercadolivre.service.ProductPurchaseService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product_purchase")
public class ProductPurchaseController {

    @Autowired
    private ProductPurchaseService purchaseService;
    
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductPurchase purchase) {
        try {
			purchaseService.save(purchase);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(422).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Estamos com indisponibilidade no sistema, favor tentar novamente mais tarde!");
		}
        return ResponseEntity.ok("Compra salva com sucesso!");
    }
}