package com.desafio.hotmart.service;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Override
    protected JpaRepository<Product, Long> getEntityRepository() {
        return productRepo;
    }

    @Override
    public void validateBeforeSave(Product entity) {

    }

    public Page<Product> findProductByName(Optional<String> name, Optional<Integer> page, Optional<String> sortBy) {
        return productRepo.findByNameOrderByScoreDescNameAscCategoryAsc(name.orElse("_") , PageRequest.of(page.orElse(0),
                PAGINATION, Sort.Direction.DESC, sortBy.orElse("score"), "name", "category" ) );
    }

    public void updateAllScores() {
        productRepo.findAll().forEach(Product::updateScore);
    }

    public void updateScoreByCategory(ProductCategory category) {
        productRepo.findAllByCategory(category).forEach(Product::updateScore);
    }

    public Page<Product> findAllProductsWithPagination(Integer page) {
      return  findAllWithPaging(PageRequest.of(page,
              PAGINATION, Sort.Direction.DESC, "name"));
    }


}
