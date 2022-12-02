package com.infodev.ecommerceproject.Repositories;

import com.infodev.ecommerceproject.Domain.ProductOrder;
import com.infodev.ecommerceproject.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> {

    ArrayList<ProductOrder> findAllByUser(User user);
    ProductOrder findProductOrderByOrderId(String orderId);
    void deleteProductOrderByOrderId(String orderId);
}
