package com.rkmgithubacc.movieapp.repository;

import com.rkmgithubacc.movieapp.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
