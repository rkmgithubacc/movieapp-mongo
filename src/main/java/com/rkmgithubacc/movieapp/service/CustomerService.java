package com.rkmgithubacc.movieapp.service;

import com.rkmgithubacc.movieapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer acceptCustomerDetails(Customer customer);
    List<Customer> acceptMultipleCustomerDetails(List<Customer> cities);
    Customer updateCustomerDetails(int id, Customer customer);
    Customer getCustomerDetails(int id);
    Customer getCustomerDetailsByFirstName(String customerFirstName);
    List<Customer> getCustomerDetailsByLastName(String customerLastName);
    void deleteCustomer(int id);
    List<Customer> getAllCustomerDetails();
}
