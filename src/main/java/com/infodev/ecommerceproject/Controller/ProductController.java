package com.infodev.ecommerceproject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infodev.ecommerceproject.Domain.Product;
import com.infodev.ecommerceproject.Domain.RequestVariables;
import com.infodev.ecommerceproject.Exception.ResourceNotFoundException;
import com.infodev.ecommerceproject.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Object addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    @GetMapping("/listProducts")
    public @ResponseBody ArrayList<Product> listProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/productDetail")
    public @ResponseBody Object productDetail(@RequestBody RequestVariables params) throws ResourceNotFoundException, JsonProcessingException {
        return productService.getProductDetails(params);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestBody Map<String,Integer> params) {
        productService.removeProduct(params.get("id"));
    }

    @GetMapping("/productPrice")
    public @ResponseBody Object productPrice(@RequestBody RequestVariables params) throws ResourceNotFoundException, JsonProcessingException {
        return productService.getProductPrice(params);
    }
}
