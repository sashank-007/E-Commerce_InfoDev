package com.infodev.ecommerceproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Validated
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "User Name cannot be blank")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid Email format")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "Date of Birth cannot be blank")
    private String dob;
    @Column(nullable = false)
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Phone Number cannot be blank")
    private int phoneNumber;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ProductOrder> productOrder;


    public User() {
    }

    public User(int id, String userId, String username, String password, @Email String email, String firstName, String lastName, String dob, String address, int phoneNumber, Set<ProductOrder> productOrder) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.productOrder = productOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getdob() {
        return dob;
    }

    public void setdob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String validateUserEmail (@Email String email) throws Exception {
        if (email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return email;
        } else {
            throw new Exception("E-mail format is invalid.");
        }
    }
}