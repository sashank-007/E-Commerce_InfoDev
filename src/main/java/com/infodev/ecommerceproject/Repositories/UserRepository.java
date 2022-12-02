package com.infodev.ecommerceproject.Repositories;

import com.infodev.ecommerceproject.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(String userId);
}
