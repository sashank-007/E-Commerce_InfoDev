package com.infodev.ecommerceproject.Services;

import com.infodev.ecommerceproject.Domain.ProductOrder;
import com.infodev.ecommerceproject.Domain.User;
import com.infodev.ecommerceproject.Exception.ResponseFormat;
import com.infodev.ecommerceproject.Repositories.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    //putInOrder
    public Object saveProductOrder(ProductOrder productOrder) {
        try {
            ProductOrder savedOrder = productOrderRepository.save(productOrder);
            String auxVerb = productOrder.getQuantity() > 2?"have":"has";
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.CREATED,
                    savedOrder.getQuantity()+" "+savedOrder.getProduct().getProductName()+" "+auxVerb+" been ordered successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("ERROR_COLUMN_VALUE", HttpStatus.BAD_REQUEST, ((DataIntegrityViolationException) ex).getRootCause().getMessage());
        }
    }

    //updateOrderStatus
    public Object updateOrder(ProductOrder productOrder) {
        try {
            ProductOrder savedOrder = productOrderRepository.save(productOrder);
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.OK,
                    savedOrder.getProduct().getProductName()+" has been updated successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("ERROR_COLUMN_VALUE", HttpStatus.BAD_REQUEST, ((DataIntegrityViolationException) ex).getRootCause().getMessage());
        }
    }

    //hard delete order entry
    public void removeOrder(String orderId) {
        productOrderRepository.deleteProductOrderByOrderId(orderId);
    }

    //getOrderDetails
    public Object getOrderDetails(String orderId) {
        ProductOrder productOrder = productOrderRepository.findProductOrderByOrderId(orderId);
        System.out.println("productOrder repository = " + productOrder);

        if (productOrder != null) {
            return productOrder;
        } else {
            return new ResponseFormat("ORDER_NOT_FOUND", HttpStatus.NOT_FOUND,"Order details not found for Order ID: "+orderId);
        }
    }

    public ArrayList<ProductOrder> getAllOrdersByUser (User user) {
        return productOrderRepository.findAllByUser(user);
    }
}
