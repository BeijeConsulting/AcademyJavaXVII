package it.beije.suormary.bookstore1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `author_id` int(11) NOT NULL,
  `editor` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id_fk_idx` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB

*/


@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "editor")
	private String editor;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "author_id")
	private Integer authorId;
	
	@Transient
	private Author author;
	
	@Transient
	private List<Integer> itemQuantity;
	
	public Book() {
		
	}
	
	public Book(String title, String description, String editor, Double price, Integer quantity, Integer authorId) {
		this.title=title;
		this.description=description;
		this.editor=editor;
		this.price=price;
		this.quantity=quantity;
		this.authorId=authorId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
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

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Integer> getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer quantity) {
		this.itemQuantity = new ArrayList<Integer>();
		for(int i=1; i<=quantity; i++) {
			this.itemQuantity.add(i);
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", title : ").append(title)
				.append(", description : ").append(description)
				.append(", editor : ").append(editor)
				.append(", price : ").append(price)
				.append(", quantity : ").append(quantity)
				.append(", authorId : ").append(authorId)
				.append(" }");
		
		return builder.toString();
	}	

}
