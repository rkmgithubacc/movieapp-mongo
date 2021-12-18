package com.rkmgithubacc.movieapp.bootstrap;

import com.rkmgithubacc.movieapp.entity.Customer;
import com.rkmgithubacc.movieapp.service.CustomerService;
import com.rkmgithubacc.movieapp.service.DbSequenceGenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
   private final CustomerService customerService;
   private final DbSequenceGenService dbSequenceGenService;

    public BootStrapData(CustomerService customerService, DbSequenceGenService dbSequenceGenService) {
        this.customerService = customerService;
        this.dbSequenceGenService = dbSequenceGenService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Started through bootstrap");
        System.out.println("Starting customer entity interaction using service: " + customerService);

        Customer customer1 = new Customer();
        customer1.setCustomerID(dbSequenceGenService.generateSequence(Customer.SEQUENCE_NAME));
        customer1.setFirstName("Raj");
        customer1.setLastName("Malhotra");
        customer1.setUserName("Rahul88");
        customer1.setPassword("welcome");
        customer1.setDateOfBirth(LocalDate.of(1988, 12, 28));
        Set<String> phoneNumbersSet1 = new HashSet<>();
        phoneNumbersSet1.add("+91-9999999999");
        phoneNumbersSet1.add("+91-9898989898");
        customer1.setPhoneNumbersSet(phoneNumbersSet1);

        System.out.println("Before saving customer: " + customer1);
        Customer savedCustomer1 = customerService.acceptCustomerDetails(customer1);
        System.out.println("After saving customer: " + savedCustomer1.toString());

        Customer customer2 = new Customer();
        customer2.setCustomerID(dbSequenceGenService.generateSequence(Customer.SEQUENCE_NAME));
        customer2.setFirstName("Ricky");
        customer2.setLastName("Ahuja");
        customer2.setUserName("Ricky01");
        customer2.setPassword("Sayonara");
        customer2.setDateOfBirth(LocalDate.of(2001, 1, 2));
        Set<String> phoneNumbersSet2 = new HashSet<>();
        phoneNumbersSet2.add("+91-9999999999");
        phoneNumbersSet2.add("+91-9898989898");
        customer2.setPhoneNumbersSet(phoneNumbersSet2);

        Customer customer3 = new Customer();
        customer3.setCustomerID(dbSequenceGenService.generateSequence(Customer.SEQUENCE_NAME));
        customer3.setFirstName("Vicky");
        customer3.setLastName("Anand");
        customer3.setUserName("Vicky01");
        customer3.setPassword("Hullabu#");
        customer3.setDateOfBirth(LocalDate.of(2008, 9, 26));
        Set<String> phoneNumbersSet3 = new HashSet<>();
        phoneNumbersSet3.add("+91-9997799999");
        phoneNumbersSet3.add("+91-98987789898");
        customer2.setPhoneNumbersSet(phoneNumbersSet3);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer2);
        customerList.add(customer3);
        List<Customer> savedCustomerList = customerService.acceptMultipleCustomerDetails(customerList);
        System.out.println("Count of saved customers: " + savedCustomerList.size());

        Customer retrievedCustomer1 = customerService.getCustomerDetails(savedCustomer1.getCustomerID());
        System.out.println("Retrieved customer by ID: " + retrievedCustomer1.toString());

        Customer retrievedCustomer2 = customerService.getCustomerDetailsByFirstName(customer2.getFirstName());
        System.out.println("Retrieved customer by First Name: " + retrievedCustomer2.toString());

        List<Customer> retrievedCustomer3 = customerService.getCustomerDetailsByLastName(customer2.getLastName());
        System.out.println("Retrieved customer count by Last Name: " + retrievedCustomer3.size());

        List<Customer> retrievedCustomerList = customerService.getAllCustomerDetails();
        System.out.println("Count of saved customers: " + retrievedCustomerList.size());

        customer1.setPassword("Welcome#123");
        Customer updatedCustomer = customerService.updateCustomerDetails(savedCustomer1.getCustomerID(), customer1);
        System.out.println("Updated customer: " + updatedCustomer.toString());

        customerService.deleteCustomer(customer3.getCustomerID());

        System.out.println("After deleting customer, available customers count: " +
                customerService.getAllCustomerDetails().size());
    }
}
