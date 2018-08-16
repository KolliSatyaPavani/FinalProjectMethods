package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {

}
