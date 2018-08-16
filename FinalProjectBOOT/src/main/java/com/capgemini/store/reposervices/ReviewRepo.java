package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Product;
import com.capgemini.store.beans.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{

}
