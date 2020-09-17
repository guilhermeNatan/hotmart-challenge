package com.desafio.hotmart.controller;

import com.desafio.hotmart.controller.requestForms.ProductRequestForm;
import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ApiOperation apiOperation;

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity getProducts() {
       return  apiOperation.transaction(() -> ResponseEntity.ok(productService.findAll()));
    }

    @PostMapping("/createProduct")
    public ResponseEntity postProduct(@RequestBody ProductRequestForm form) {

        return  apiOperation.transaction(() -> {
            Product product = new Product();
            product.setName(form.getName());
            product.setDescription(form.getDescription());
            productService.saveAndFlush(product);
            return ResponseEntity.ok("Success on create product");
        });
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity updateProduct( @PathVariable("id") Long id,
                                         @RequestBody ProductRequestForm form) {
        return apiOperation.transaction(() -> {
            Product product = productService.findOne(id);
            product.setDescription(form.getDescription());
            product.setName(form.getName());
            productService.saveAndFlush(product);
            return ResponseEntity.ok(product);
        });
    }


}

