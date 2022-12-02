package com.infodev.ecommerceproject.Controller;

import com.infodev.ecommerceproject.Domain.ProductOrder;
import com.infodev.ecommerceproject.Repositories.ProductOrderRepository;
import com.infodev.ecommerceproject.Repositories.ProductRepository;
import com.infodev.ecommerceproject.Repositories.UserRepository;
import com.infodev.ecommerceproject.Services.ProductOrderService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class ProductOrderController {


    @Autowired private ProductOrderService productOrderService;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ProductController productController;

    @PostMapping("/addOrder")
    public @ResponseBody Object saveProductOrder(@RequestBody ProductOrder productOrder) {
//        ProductOrder productOrder = new ProductOrder();
//        productOrder.setOrderId(RandomString.make(12)+"_"+new Random().nextInt(10000000));
//        if (params.get("defaultAddress").equalsIgnoreCase("true")){
//            productOrder.setDeliveryAddress(params.get("deliveryAddress"));
//        } else {
////            productOrder.setDeliveryAddress(userRepository.getUserById(params.get("userId")));
//        }
//        productOrder.setQuantity(Integer.parseInt(params.get("quantity")));
//        productOrder.setTotalPrice(productOrder.getQuantity() * productController.getProductPrice(Integer.parseInt(params.get("productId"))));
        return productOrderService.saveProductOrder(productOrder);
//        productOrderRepository.findProductOrderByUser(userRepository.findById(Integer.valueOf(params.get("userId"))).get());
    }
}
