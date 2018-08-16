package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Discount;

public interface DiscountRepo extends JpaRepository<Discount, Integer>{

}
