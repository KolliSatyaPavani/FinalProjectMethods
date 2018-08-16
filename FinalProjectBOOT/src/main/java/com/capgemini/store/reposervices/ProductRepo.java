package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Customer;
import com.capgemini.store.beans.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
