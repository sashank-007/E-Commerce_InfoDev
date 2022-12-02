# E-Commerce_InfoDev
A E-commerce platform created using Sprint Boot, JPA, Hibernate, MySQL, RESTful APIs and Swagger

**Project Specifications**:
* Java 8
* Gradle 6.9
* Spring Boot 2.7.5
* Spring JPA
* Spring Web
* Springfox boot starter 3.0 (Swagger, Swagger UI)
* Google code JSON Library

**Database Specification | application.properties:**
  * Database name = ecommerce_project
  * Database driver-class = com.mysql.cj.jdbc.Driver

>Default port = 8081

**Entities**:
* User
* Product
* Product-Order

**Features**:
* Basic CRUD for above entities:
    * Register, Update, Delete and Get User/User details
    * Add, Update, Delete and Get Product/Product details
    * Create, Update, Delete/Cancel and Get Order/Order details.
* Communication between these modules:
  * User can browse products based on their name, category and/or price.
  * User can create a product-order, check order status and cancel order.
    * User can opt for default address or new delivery address.
    * Total price is calculated based on the order quantity and product price.
    * Product stock is updated accordingly after order.

**API Documentation:** http://**[host IP/hostname]**:**[port]**/swagger-ui/index.html (Eg: http://localhost:8081/swagger-ui/index.html

**Request Samples:**

1. Getting User Details from User ID
> localhost:8081/userRequest
```json
{
  "user":{
    "userId": "sashank5215_2970"
  }
}
```

2. Getting Order Details from Order ID
> localhost:8081/orderDetail
```json
{
  "order": {
    "orderId": "[orderId-value]"
  }
}
```
3. Creating a new product order
> localhost:8081/orderProduct
```json
{
  "order": {
    "quantity": "5"
  },
  "product": {
    "productId": "[productId-value]"
  },
  "user": {
    "userId": "[userId-value]"
  },
  "useDefaultAddress": true,
  "action": "[blank/customSearch]"
}
```