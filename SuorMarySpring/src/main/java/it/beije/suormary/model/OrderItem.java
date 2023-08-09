package it.beije.suormary.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Integer orderId;
    
    @OneToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Integer bookId;

    @Column(name = "price")
    private double price;

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder("{ ")
                .append("id : ").append(id)
                .append(", orderId : ").append(orderId)
                .append(", bookId : ").append(bookId)
                .append(", price : ").append(price)
                .append(", quantity : ").append(quantity)
                .append(" }");

        return builder.toString();
    }
}