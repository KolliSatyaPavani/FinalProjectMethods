package com.capgemini.store.reposervices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Cart;
import com.capgemini.store.beans.Product;

public interface CartRepo  extends JpaRepository<Cart, Integer>{
	/*public List<Product> findByCartId(int cartId);*/
}
