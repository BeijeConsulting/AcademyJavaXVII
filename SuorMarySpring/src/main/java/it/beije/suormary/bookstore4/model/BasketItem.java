package it.beije.suormary.bookstore4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "baskets")
public class BasketItem {
	
	
//	@ManyToOne
//    @JoinColumn(name = "book_id" , referencedColumnName = "id") // Nome della colonna FK in tabella1
//    private Book book;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "quantity")
	private int quantity;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}

	@Override
	public String toString() {
		return "Basket [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
	}

	



	


	
}
