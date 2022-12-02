package com.infodev.ecommerceproject.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infodev.ecommerceproject.Domain.Product;
import com.infodev.ecommerceproject.Domain.ProductOrder;
import com.infodev.ecommerceproject.Domain.RequestVariables;
import com.infodev.ecommerceproject.Domain.User;
import com.infodev.ecommerceproject.EcommerceProjectApplication;
import com.infodev.ecommerceproject.Exception.ResponseFormat;
import com.infodev.ecommerceproject.Exception.ResourceNotFoundException;
import com.infodev.ecommerceproject.Repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderService productOrderService;

    public static Logger log = Logger.getLogger(EcommerceProjectApplication.class.getName());

    public Object addUser(@Validated @Email User user) throws Exception {
        user.setuserId(user.getUsername()+"_"+new Random().nextInt(100000));
        user.validateUserEmail(user.getEmail());
        try {
            User savedUser = userRepository.save(user);
            log.info("User saved as <== User ID ==> "+savedUser.getuserId()+" <== User name ==> "+savedUser.getUsername());
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.CREATED,"User has been saved with User ID: "+savedUser.getuserId()+" and User Name: "+savedUser.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("ERROR_COLUMN_VALUE", HttpStatus.BAD_REQUEST, ((DataIntegrityViolationException) ex).getRootCause().getMessage());
        }
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public Object getUserDetails(String userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                System.out.println("User not found: "+userId);
                throw new ResourceNotFoundException(new ResponseFormat("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "User not found for the given User ID: " + userId));
            } else {
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("USER_NOT_FOUND", HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    public Object removeUser(String userId) {
        Object user = getUserDetails(userId);
        if (user instanceof User) {
            try {
                userRepository.delete((User) user);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseFormat("REQUEST_ERROR", HttpStatus.BAD_REQUEST,"An error has occurred while deleting User ID " + userId);
            }
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.OK,"User ID: "+userId+" has been deleted.");
        } else {
            return user;
        }
    }

    //getAllProducts or browse
    public ArrayList<Product> browseProducts() {
        return productService.getAllProducts();
    }


    //orderProducts
    public Object createOrder(RequestVariables params) throws ResourceNotFoundException {
        Object user = getUserDetails(params.getUser().getuserId());
        Object product = productService.getProductDetails(params);
        if (user instanceof User && product instanceof Product) {
            ProductOrder order = new ProductOrder();
            Product productDetail = (Product) product;
            User userDetail = (User) user;
            if (productDetail.getStock() < 1) {
                return new ResponseFormat("PRODUCT_OUT_OF_STOCK", HttpStatus.FORBIDDEN,"Product " + productDetail.getProductName() + " OUT OF STOCK!!!");
            }

            boolean defaultAddress = params.getUseDefaultAddress() != null && params.getUseDefaultAddress();
            if (params.getOrder().getQuantity() >= 1 && params.getOrder().getQuantity() <= productDetail.getStock()) {
                order.setOrderId(RandomString.make(14)+"_"+new Random().nextInt(1000000));
                order.setProduct(productDetail);
                order.setUser(userDetail);
                order.setQuantity(params.getOrder().getQuantity());
                order.setDeliveryAddress(defaultAddress ? userDetail.getAddress() : params.getDeliveryAddress());
                order.setTotalPrice(productDetail.getPrice() * params.getOrder().getQuantity());
                order.setOrderStatus("Processing");
                ResponseFormat savedOrder = (ResponseFormat) productOrderService.saveProductOrder(order);
                if (savedOrder.getResponseCode().equalsIgnoreCase("request_successful")) {
                    productDetail.setStock(productDetail.getStock() - order.getQuantity());
                    productService.updateProduct(productDetail);
                } else {
                    return savedOrder;
                }
            } else {
                return new ResponseFormat("ERROR_REQUEST_QUANTITY", HttpStatus.BAD_REQUEST,"Error processing your order request!");
            }
        }
        return (user instanceof User)?product:user;
    }

    //getAllOrders
    public Object getAllUserOrders (RequestVariables params) {
        Object userDetail = getUserDetails(params.getUser().getuserId());
        if (userDetail instanceof User) {
            return productOrderService.getAllOrdersByUser((User) userDetail);
        } else {
            return userDetail;
        }
    }

    //getOrderDetails
    public Object getOrderDetails(RequestVariables params) {
        return productOrderService.getOrderDetails(params.getOrder().getOrderId());
    }
//
//    public ProductOrder validateOrderDetails (String orderId) {
//        Object productOrder = productOrderService.getOrderDetails(orderId);
//        if (productOrder instanceof ProductOrder) {
//            ProductOrder orderDetail = (ProductOrder) productOrder;
//            return orderDetail;
//        }
//        return productOrder;
//    }

    //checkStatus
    public Object getOrderStatus(RequestVariables params) {
        Object productOrder = productOrderService.getOrderDetails(params.getOrder().getOrderId());
        if (productOrder instanceof ProductOrder) {
            ProductOrder orderDetail = (ProductOrder) productOrder;
            JSONObject json = new JSONObject();
            json.put("userId", orderDetail.getUser().getuserId());
            json.put("orderId", orderDetail.getOrderId());
            json.put("product", orderDetail.getProduct().getProductName());
            json.put("totalPrice", orderDetail.getTotalPrice());
            json.put("orderStatus", orderDetail.getOrderStatus());
            return json;
        } else {
            return productOrder;
        }
    }

    //cancelOrder
    public Object cancelOrder(RequestVariables params) {
        Object productOrder = productOrderService.getOrderDetails(params.getOrder().getOrderId());
        if (productOrder instanceof ProductOrder && params.getAction().equalsIgnoreCase("cancel")){
            ProductOrder orderDetail = (ProductOrder) productOrder;
            orderDetail.setOrderStatus("canceled");
            productOrderService.updateOrder(orderDetail);
            return getOrderStatus(params);

        }
        return productOrder;
    }
}
