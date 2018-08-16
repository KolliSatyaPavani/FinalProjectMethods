package com.capgemini.store.services;

import com.capgemini.store.beans.Address;
import com.capgemini.store.beans.Cart;
import com.capgemini.store.beans.Customer;
import com.capgemini.store.exceptions.CustomerNotFoundException;
import com.capgemini.store.exceptions.InvalidInputException;

public interface CapStoreCustomerService {

	Customer signUp(Customer customer);
	String forgotPassword(String mobileNumber) throws CustomerNotFoundException;
	String securityQuestion(String securityAnswer) throws InvalidInputException;
	void onlinePayment(String cardNumber, String customerPhoneNumber);
	void addToCart(int productId, int cartId, int quantity);
	Cart updateCart(int productId, int cartId, int quantity);
	Cart removeProductFromCart(int productId, int cartId);//once removed from cart, update stock & quantity in product
	void updateQuantity(int productId, int quantityOrdered, int orderId);
	void addAddress(Address address);
	Address getAddress(int addressId);
	double applyDiscount(int productId);
	double applyCoupon(int cartId);
}
