package com.desafio.hotmart.controller;

import com.desafio.hotmart.controller.requestForms.ProductRequestForm;
import com.desafio.hotmart.controller.requestForms.SearchResponseForm;
import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.exception.ProductException;
import com.desafio.hotmart.reuse.factories.ProductFactory;
import com.desafio.hotmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ApiOperation apiOperation;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFactory productFactory;

    @GetMapping("/list")
    public ResponseEntity getProducts(@RequestParam Optional<Integer> page) {
        return  apiOperation.transaction(() -> {
            Page<Product> products = productService.findAllProductsWithPagination(page.orElse(0));
           return ResponseEntity.ok(products);
        });
    }

    @PostMapping("/insert")
    public ResponseEntity postProduct(@Valid  @RequestBody ProductRequestForm form) {
        try {
            Product product = productFactory.createFromProductRequestForm(form);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return  ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct( @PathVariable("id") Long id,
                                         @RequestBody ProductRequestForm form) {
        Product product = productService.findOne(id);
        try {
            productFactory.setProductFieldsFormProductRequestForm(product, form);
            productService.saveAndFlush(product);
        } catch (ProductException e) {
            ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(product);
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok("Produto excluído com sucesso");

        }catch (Exception e) {
            return  ResponseEntity.ok("Falha ao excluir produto: " + e.getMessage());
        }
    }


}

