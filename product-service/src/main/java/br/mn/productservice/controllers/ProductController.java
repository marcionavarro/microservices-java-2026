package br.mn.productservice.controllers;

import br.mn.productservice.dtos.ProductDTO;
import br.mn.productservice.entities.ProductEntity;
import br.mn.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(
        @PathVariable Long id,
        @RequestParam String targetCurrency
    ) throws Exception {
        targetCurrency = targetCurrency.toUpperCase();

        ProductEntity entity = repository.findById(id)
            .orElseThrow(() -> new Exception("Product not found"));

        Double convertedPrice = null;
        String environment = "Product-service running on Port: " + port;
        String requestCurrency = targetCurrency;

        ProductDTO dto = new ProductDTO(
            entity.getId(),
            entity.getDescription(),
            entity.getBrand(),
            entity.getModel(),
            entity.getCurrency(),
            entity.getPrice(),
            entity.getStock(),
            convertedPrice,
            environment,
            requestCurrency
        );

        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String message = e.getMessage().replace("/r/n", "");
        return ResponseEntity.badRequest().body(message);
    }

}
