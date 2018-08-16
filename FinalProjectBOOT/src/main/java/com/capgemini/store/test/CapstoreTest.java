package com.capgemini.store.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.store.beans.Customer;
import com.capgemini.store.services.CapStoreCustomerService;
import com.capgemini.store.services.CapStoreCustomerServiceImpl;

public class CapstoreTest {

CapStoreCustomerService  capstoreCustomerService;
	

	
	public CapstoreTest() {
	
	capstoreCustomerService = new CapStoreCustomerServiceImpl();
	}


	@Test
	public void contextLoads() throws Exception {
		Customer cust=new Customer("7799421508", "kudupudi.nam", "mama", "1234", "Whats ur clg?", "vnr");
		capstoreCustomerService.signUp(cust);
		String question=capstoreCustomerService.forgotPassword(cust.getPhoneNumber());
		assertEquals(question,cust.getSecurityQuestion());
	}
}
