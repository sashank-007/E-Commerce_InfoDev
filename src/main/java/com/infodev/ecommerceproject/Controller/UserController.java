package com.infodev.ecommerceproject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infodev.ecommerceproject.Domain.Product;
import com.infodev.ecommerceproject.Domain.RequestVariables;
import com.infodev.ecommerceproject.Domain.User;
import com.infodev.ecommerceproject.Exception.ResponseFormat;
import com.infodev.ecommerceproject.Exception.ResourceNotFoundException;
import com.infodev.ecommerceproject.Services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    @ApiOperation(value = "Adds new user in the table",
            notes = "Must provide all user information",
            response = User.class)
    public @ResponseBody Object registerUser(@RequestBody @Validated User user){
        try {
            return userService.addUser(user);
        } catch (Exception e) {
            return new ResponseFormat("INVALID_EMAIL_FORMAT", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/listUsers")
    public @ResponseBody ArrayList<User> listUsers() throws ResourceNotFoundException {
        return userService.getAllUsers();
    }

    @GetMapping("/userDetail/{id}")
    public @ResponseBody Object userDetails(@RequestBody RequestVariables params) throws ResourceNotFoundException {
        return userService.getUserDetails(params.getUser().getuserId());
    }

    @GetMapping("/userRequest")
    @ApiOperation(value = "Retrieves user information based on the provided ID in the request",
            notes = "Only requires userId inside a user object",
            response = User.class)
    public @ResponseBody Object userDetailsRequest(@RequestBody RequestVariables params) throws ResourceNotFoundException {
        return userService.getUserDetails(params.getUser().getuserId());
    }

    @GetMapping("/browseAll")
    public @ResponseBody ArrayList<Product> browseProducts(){
        return userService.browseProducts();
    }

    @DeleteMapping("/deleteUser")
    public Object deleteUser(@RequestBody RequestVariables params) throws ResourceNotFoundException {
        return userService.removeUser(params.getUser().getuserId());
    }

    @PostMapping("/orderProduct")
    public @ResponseBody Object orderProduct(@RequestBody RequestVariables params) throws ResourceNotFoundException, JsonProcessingException {
        return userService.createOrder(params);
    }

    @GetMapping("/myOrder")
    public @ResponseBody Object userOrders(@RequestBody RequestVariables params) throws ResourceNotFoundException {
        return userService.getAllUserOrders(params);
    }

    @GetMapping("/orderDetail")
    public @ResponseBody Object orderDetails(@RequestBody RequestVariables params) throws JsonProcessingException {
        Object productOrder = userService.getOrderDetails(params);
        System.out.println("productOrder = " + new ObjectMapper().writeValueAsString(productOrder));

        return productOrder;
    }

    @GetMapping("/orderStatus")
    public @ResponseBody Object orderStatus(@RequestBody RequestVariables params) {
        return userService.getOrderStatus(params);
    }

    @PutMapping("/cancelOrder")
    public @ResponseBody Object cancelOrder(@RequestBody RequestVariables params) {
        return userService.cancelOrder(params);
    }


//    @GetMapping("/login")
//    public String login(String username, String password) {
//        Map<String, String> params = new HashMap<>();
//
//        params.put("username", username);
//        params.put("password",password);
//
//        UserService userService = new UserService();
//
//        User userMatch = userService.login(params);
//        if (userMatch != null) {
//            return "Welcome "+userMatch.getFirstName()+" "+userMatch.getLastName();
//        } else {
//            return "You are not authorized";
//        }
//    }
}
