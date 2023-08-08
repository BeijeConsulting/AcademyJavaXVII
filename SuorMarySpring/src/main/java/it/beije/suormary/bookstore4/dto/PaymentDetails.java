package it.beije.suormary.bookstore4.dto;

import java.io.Serializable;

public class PaymentDetails  implements Serializable {

	private static final long serialVersionUID = -2965313686186766776L;
	
	private String shippingAddress;
    private String payment;
    
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
}
