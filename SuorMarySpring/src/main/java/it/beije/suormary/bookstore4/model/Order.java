package it.beije.suormary.bookstore4.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonProperty(value = "date")
	@Column(name = "date")
	private LocalDateTime date;

	@JsonIgnore
	@Column(name = "user_id")
	private int userId;

	@Column(name = "status")
	private String status;
	/*
	 * I - inserted
	 * P - paid
	 * C - cancelled
	 */

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "shipping_address")
	private String shippingAddress;

	@Transient
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
	
	@JsonProperty(value = "date")
	public String getDateAsString() {
		return date != null ? date.toString() : null;
	}

	@JsonProperty(value = "date")
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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
