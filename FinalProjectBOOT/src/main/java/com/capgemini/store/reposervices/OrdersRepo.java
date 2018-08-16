package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
