package com.rkmgithubacc.movieapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "customer")
public class Customer {
    @Id
    private int customerID;

    /*
        We have a static field SEQUENCE_NAME, which is a unique reference to
        the auto-incremented sequence for the customer collection.
        We also annotate it with the @Transient to prevent it from
        being persisted alongside other properties of the model.
    */
    @Transient
    public static final String SEQUENCE_NAME = "customer_seq";

    @Field(name = "first_name")
    @Size(max = 20)
    @NotNull(message = "First name must not be null")
    private String firstName;

    @Field(name = "last_name")
    @Size(max = 20)
    @Nullable
    private String lastName;

    @Field(name = "username")
    @Size(max = 20)
    @NotNull(message = "User name must not be null")
    @Indexed(unique = true)
    private String userName;

    @Field(name = "password")
    @Size(min = 5, max = 20)
    @NotNull(message = "Password can not be null")
    private String password;

    @Field(name = "date_of_birth")
    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @Field(name = "phone_number")
    @NotNull(message = "Phone Number cannot be null")
    private Set<String> phoneNumbersSet = new HashSet<>();

    public Customer() {
    }

    public Customer(String firstName, @Nullable String lastName, String userName, String password, LocalDate dateOfBirth, Set<String> phoneNumbersSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbersSet = phoneNumbersSet;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getPhoneNumbersSet() {
        return phoneNumbersSet;
    }

    public void setPhoneNumbersSet(Set<String> phoneNumbersSet) {
        this.phoneNumbersSet = phoneNumbersSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumbersSet=" + phoneNumbersSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerID == customer.customerID;
    }

    @Override
    public int hashCode() {
        return customerID;
    }
}
