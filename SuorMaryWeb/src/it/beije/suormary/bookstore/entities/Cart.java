package it.beije.suormary.bookstore.entities;



import java.util.List;


public class Cart {

	private Integer userId;
		
	private List<CartItem> items;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
}
