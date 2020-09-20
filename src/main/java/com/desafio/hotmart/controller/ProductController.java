package com.desafio.hotmart.controller;

import com.desafio.hotmart.controller.requestForms.ProductRequestForm;
import com.desafio.hotmart.controller.requestForms.SearchResponseForm;
import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ApiOperation apiOperation;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity getProducts() {
       return  apiOperation.transaction(() -> ResponseEntity.ok(productService.findAll()));
    }

    @PostMapping("/insert")
    public ResponseEntity postProduct(@RequestBody ProductRequestForm form) {

        return  apiOperation.transaction(() -> {
            Product product = new Product();
            product.setName(form.getName());
            product.setDescription(form.getDescription());
            productService.saveAndFlush(product);
            return ResponseEntity.ok("Success on create product");
        });
    }

    @PutMapping("/update/{id}")
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

    @GetMapping("/find")
    public ResponseEntity find(@RequestParam Optional<String> name,
                              @RequestParam Optional<Integer> page,
                              @RequestParam Optional<String> sortBy) {

        Page<Product> pageProducts = productService.findProductByName(name, page, sortBy);
        SearchResponseForm searchResponseForm = new SearchResponseForm();
        searchResponseForm.setPage(pageProducts.getNumber() );
        searchResponseForm.setDataAtual(Calendar.getInstance());
        searchResponseForm.setTermoPesquisado(name.orElse(" "));
        searchResponseForm.getConteudo().addAll(pageProducts.getContent());
        return ResponseEntity.ok(searchResponseForm);
    }


    @GetMapping("/updadteScore")
    public ResponseEntity find() {
        productService.updateAllScores();
        return ResponseEntity.ok("ok");
    }
}

