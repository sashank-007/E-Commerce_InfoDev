package com.infodev.ecommerceproject.Services;

import com.infodev.ecommerceproject.Domain.Product;
import com.infodev.ecommerceproject.Domain.RequestVariables;
import com.infodev.ecommerceproject.Exception.ResourceNotFoundException;
import com.infodev.ecommerceproject.Exception.ResponseFormat;
import com.infodev.ecommerceproject.Repositories.ProductRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //addProducts
    public Object addProduct(@RequestBody Product product) {
        product.setProductId(product.getProductName()+"_"+ RandomString.make(12));
        try {
            Product savedProduct = productRepository.save(product);
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.CREATED,
                    "Product has been saved with Product ID: "+savedProduct.getProductId()+
                            " and Product Name: "+savedProduct.getProductName());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("ERROR_COLUMN_VALUE", HttpStatus.BAD_REQUEST, ((DataIntegrityViolationException) ex).getRootCause().getMessage());
        }
    }

    //getAllProducts
    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    //getProductDetails
    public Object getProductDetails(RequestVariables params) {
        Product productDetail = null;
        if (params.getProduct() != null) {
            try {
                String productName = params.getProduct().getProductName();
                String category = params.getProduct().getCategory();
                String productId = params.getProduct().getProductId();
                if (params.getAction().equalsIgnoreCase("customsearch")) {
                    if (params.getProduct().getProductName() != null || params.getProduct().getCategory() != null) {
                        productDetail = productRepository.findByProductNameOrCategory(productName, category);
                    } else if (params.getProduct().getPrice() >= 0) {
                        productDetail = productRepository.findProductByPriceBetween(0, params.getProduct().getPrice());
                    }
                }
                if (params.getProduct().getProductId() != null){
                    productDetail = productRepository.findByProductIdOrProductNameOrCategory(productId, productName, category);
                }
                if (productDetail == null){
                    throw new ResourceNotFoundException(new ResponseFormat("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND,"Product not found for the provided details"));
                } else {
                    return productDetail;
                }
            }catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseFormat("REQUEST_ERROR", HttpStatus.BAD_REQUEST,ex.getMessage());
            }
        } else {
            return new Exception(new ResponseFormat("INVALID_REQUEST", HttpStatus.BAD_REQUEST,"Product search category not found in the request").toString());
        }
    }


    //updateProducts
    public Object updateProduct(Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            return new ResponseFormat("REQUEST_SUCCESSFUL", HttpStatus.OK,
                    savedProduct.getProductName()+" has been updated successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseFormat("ERROR_COLUMN_VALUE", HttpStatus.BAD_REQUEST, ((DataIntegrityViolationException) ex).getRootCause().getMessage());
        }
    }

    //deleteProducts
    public void removeProduct(int id) {
        productRepository.deleteById(id);
    }




    //getProductOrderHistory

    //getProductPrice
    public Object getProductPrice(RequestVariables params) throws ResourceNotFoundException {
        Object product = getProductDetails(params);
        return product instanceof Product?((Product)product).getPrice():product;
    }
}
