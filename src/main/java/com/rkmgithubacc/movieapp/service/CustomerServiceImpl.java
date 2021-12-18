package com.rkmgithubacc.movieapp.service;

import com.rkmgithubacc.movieapp.entity.Customer;
import com.rkmgithubacc.movieapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    // Dependency Injection using constructor
    /*
        As of Spring 4.3, classes with a single constructor can omit the @Autowired annotation.
        This is a nice bit of convenience and boilerplate removal.
        On top of that, also starting with 4.3, we can leverage the constructor-based injection in
        @Configuration annotated classes.
        In addition, if such a class has only one constructor, we can omit the @Autowired annotation as well.
    */
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer acceptCustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> acceptMultipleCustomerDetails(List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            customers.set(
                    i,
                    acceptCustomerDetails(customers.get(i))
            );
        }
        return customers;
    }

    @Override
    public Customer updateCustomerDetails(int id, Customer customer) {
        Customer savedCustomer = getCustomerDetails(id);
        savedCustomer.setFirstName(customer.getFirstName());
        savedCustomer.setLastName(customer.getLastName());
        savedCustomer.setDateOfBirth(customer.getDateOfBirth());
        savedCustomer.setUserName(customer.getUserName());
        savedCustomer.setPassword(customer.getPassword());
        savedCustomer.setPhoneNumbersSet(customer.getPhoneNumbersSet());
        acceptCustomerDetails(savedCustomer);
        return savedCustomer;
    }

    @Override
    public Customer getCustomerDetails(int id) {
        return customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() : new Customer();
    }

    @Override
    public Customer getCustomerDetailsByFirstName(String customerFirstName) {
        return customerRepository.findByFirstName(customerFirstName);
    }

    @Override
    public List<Customer> getCustomerDetailsByLastName(String customerLastName) {
        return customerRepository.findByLastName(customerLastName);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer retrievedCustomer = getCustomerDetails(id);
        customerRepository.delete(retrievedCustomer);
    }

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerRepository.findAll();
    }
}
