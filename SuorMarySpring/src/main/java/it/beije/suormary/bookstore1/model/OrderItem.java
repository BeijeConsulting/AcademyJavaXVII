package it.beije.suormary.bookstore1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/*

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `book_id_fk_idx` (`book_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB

*/

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "order_id")
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private Integer quantity;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", orderId : ").append(orderId)
				.append(", book : ").append(book)
				.append(", price : ").append(price)
				.append(", quantity : ").append(quantity)
				.append(" }");
		
		return builder.toString();
	}	
}