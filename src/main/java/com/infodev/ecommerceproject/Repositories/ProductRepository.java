package com.infodev.ecommerceproject.Repositories;

import com.infodev.ecommerceproject.Domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByProductIdOrProductNameOrCategory(String productId,String productName, String category);

    Product findByProductNameOrCategory(String productName, String category);

    Product findProductByPriceBetween(double minPrice,double maxPrice);
}
