package it.beije.suormary.bookstore1.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/*

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` char(1) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `shipping_address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB

*/

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "status")
	private String status;
	/*
	 * I - Inserted
	 * P - Paid
	 * C - Cancelled
	 */

	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "shipping_address")
	private String shippingAddress;
	
	
	@OneToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY)
	//@OneToMany(targetEntity = ContactDetail.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_order")
	private List<OrderItem> items;


	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	


	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", userId : ").append(userId)
				.append(", date : ").append(date)
				.append(", status : ").append(status)
				.append(", amount : ").append(amount)
				.append(", shippingAddress : ").append(shippingAddress)
				.append(", items : ").append(items)
				.append(" }");
		
		return builder.toString();
	}	
	
}
