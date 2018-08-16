package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer>{

}
