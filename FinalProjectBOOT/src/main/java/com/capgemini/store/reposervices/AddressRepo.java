package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Address;

public interface AddressRepo  extends JpaRepository<Address, Integer>{

}
