package com.rkmgithubacc.movieapp.repository;

import com.rkmgithubacc.movieapp.entity.Customer;

public abstract class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public <S extends Customer> S save(S entity) {
        return null;
    }
}
