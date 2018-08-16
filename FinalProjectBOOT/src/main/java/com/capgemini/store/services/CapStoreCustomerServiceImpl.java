package com.capgemini.store.services;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.store.beans.Address;
import com.capgemini.store.beans.Cart;
import com.capgemini.store.beans.Coupon;
import com.capgemini.store.beans.Customer;
import com.capgemini.store.beans.Discount;
import com.capgemini.store.beans.Orders;
import com.capgemini.store.beans.Product;
import com.capgemini.store.beans.Review;
import com.capgemini.store.exceptions.CustomerNotFoundException;
import com.capgemini.store.exceptions.InvalidInputException;
import com.capgemini.store.reposervices.AddressRepo;
import com.capgemini.store.reposervices.CartRepo;
import com.capgemini.store.reposervices.CouponRepo;
import com.capgemini.store.reposervices.CustomerRepo;
import com.capgemini.store.reposervices.DiscountRepo;
import com.capgemini.store.reposervices.OrdersRepo;
import com.capgemini.store.reposervices.ProductRepo;
import com.capgemini.store.reposervices.ReviewRepo;
@Component
public class CapStoreCustomerServiceImpl implements CapStoreCustomerService {

	Customer customer;
	Review review;
	Product product;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ReviewRepo reviewRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private DiscountRepo discountRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Override
	public Customer signUp(Customer customer) {
		return customerRepo.save(customer);
		}

	@Override
	public String forgotPassword(String mobileNumber) throws CustomerNotFoundException{
		
		
			customer = customerRepo.getOne(mobileNumber);
			if(customer==null)
				throw new CustomerNotFoundException("customer not found with mobile no.");
			else
			return customer.getSecurityQuestion();
	}

	@Override//encryption 
	public String securityQuestion(String securityAnswer) throws InvalidInputException {
		if(securityAnswer.equals(customer.getSecurityAnswer()))
		{
			return customer.getPassword();
		}
		else
			throw new InvalidInputException("Invalid answer");
	}

	@Override
	public void onlinePayment(String cardNumber, String customerPhoneNumber) {
		Customer cust = new Customer();
		cust = customerRepo.getOne(customerPhoneNumber);
		cust.setCardNumber(cardNumber);
		customerRepo.save(cust);
		
	}

	@Override
	public void addToCart(int productId, int cartId, int quantity) {
		Product product= productRepo.getOne(productId);
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		product.setCartQuantity(quantity);
	    products.add(product);
		cart.setProducts(products);
		cartRepo.save(cart);
		}

	@Override
	public Cart updateCart(int productId, int cartId, int quantity) {
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		int productIndex= products.indexOf(new Product(productId));
		Product product= products.get(productIndex);
		product.setCartQuantity(quantity);
		products.set(productIndex, product);
		cart.setProducts(products);
		return cartRepo.save(cart);
	}

	@Override
	public Cart removeProductFromCart(int productId, int cartId) {
		Cart cart = cartRepo.getOne(cartId);
		List<Product> products =cart.getProducts();
		int productIndex =products.indexOf(new Product(productId));
		products.remove(productIndex);
		cart.setProducts(products);
		return cartRepo.save(cart);
		
	}

	@Override
	public void addAddress(Address address) {
		addressRepo.save(address);
		
	}

	@Override
	public Address getAddress(int addressId) {
		return addressRepo.getOne(addressId);
	}

	@Override
	public void updateQuantity(int productId, int quantityOrdered, int orderId) {
		Orders order = ordersRepo.getOne(orderId);
		List<Product> products =order.getProducts();
		int productIndex =products.indexOf(new Product(productId));
		Product product =products.get(productIndex);
		int quantityLeft=0;
		if(order.getDeliveryStatus()!=null && order.isRefundRequest()==false)
		{
			quantityLeft = product.getProductQuantityAvailable()-quantityOrdered;
			if(quantityLeft==0)
			{
				product.setProductStatus(true);// product is now out of stock
			}
			product.setProductQuantityAvailable(quantityLeft);
			productRepo.save(product);
		}
		if(order.isRefundRequest()==true)
		{
			if(isRefundRequestValid(order))
			{
				quantityLeft = product.getProductQuantityAvailable()+quantityOrdered;
				product.setProductQuantityAvailable(quantityLeft);
				product.setProductStatus(false); // product is in stock
				productRepo.save(product);
				
			}
		}
		
	}

	public boolean isRefundRequestValid(Orders order) {
		Date date1 =order.getElligibleReturnDate();
		Date date2 = order.getRefundRequestDate();
		if(date1.after(date2))
		{return false;}
		else
			return true;
		
		
	}

	@Override //if discount is valid, we will get the discounted product price  
	//else we get the original product price
	public double applyDiscount(int productId) {
		Product product =productRepo.getOne(productId);
		Discount discount = product.getDiscount();
		double price = product.getProductPrice();
		double finalPrice=price;
		if(discountIsValid(discount))
		{
			double percentDiscount =discount.getPercentDiscount();
			finalPrice=price-((price*percentDiscount)/100);
			product.setProductPrice(finalPrice);
		}
		return finalPrice;
	}

	public boolean discountIsValid(Discount discount) {
		Date date2 = discount.getEndDateOfDiscount();
		Date date1 = discount.getStartDateOfDiscount();
		if(date1.before(new Date()))
		{
			if(date2.after(new Date()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	//if coupon is valid, we will get the discounted cart price  
		//else we get the original cart price
	public double applyCoupon(int cartId) {
		Cart cart = cartRepo.getOne(cartId);
		Coupon coupon = cart.getCoupon();
		double cartAmount = cart.getTotalAmount();
		double finalPrice = cartAmount; 
		if(couponIsValid(coupon))
		{
			double couponDiscount = coupon.getCouponDiscountValue();
			finalPrice=cartAmount-((cartAmount*couponDiscount)/100);
			cart.setTotalAmount(finalPrice);
		}
		return finalPrice;
	}

	public boolean couponIsValid(Coupon coupon) {
		Date date2 = coupon.getCouponEndDate();
		Date date1 = coupon.getCouponStartDate();
		if(date1.before(new Date()))
		{
			if(date2.after(new Date()))
			{
				return true;
			}
		}
		return false;
	}
	
	}
