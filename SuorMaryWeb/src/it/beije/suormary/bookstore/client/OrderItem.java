
package it.beije.suormary.bookstore.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per orderItem complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="orderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="book" type="{http://server.bookstore.suormary.beije.it/}book" minOccurs="0"/>
 *         &lt;element name="bookId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderItem", propOrder = {
    "book",
    "bookId",
    "id",
    "orderId",
    "price",
    "quantity"
})
public class OrderItem {

    protected Book book;
    protected int bookId;
    protected int id;
    protected int orderId;
    protected double price;
    protected int quantity;

    /**
     * Recupera il valore della propriet� book.
     * 
     * @return
     *     possible object is
     *     {@link Book }
     *     
     */
    public Book getBook() {
        return book;
    }

    /**
     * Imposta il valore della propriet� book.
     * 
     * @param value
     *     allowed object is
     *     {@link Book }
     *     
     */
    public void setBook(Book value) {
        this.book = value;
    }

    /**
     * Recupera il valore della propriet� bookId.
     * 
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Imposta il valore della propriet� bookId.
     * 
     */
    public void setBookId(int value) {
        this.bookId = value;
    }

    /**
     * Recupera il valore della propriet� id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet� id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet� orderId.
     * 
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Imposta il valore della propriet� orderId.
     * 
     */
    public void setOrderId(int value) {
        this.orderId = value;
    }

    /**
     * Recupera il valore della propriet� price.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Imposta il valore della propriet� price.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Recupera il valore della propriet� quantity.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Imposta il valore della propriet� quantity.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
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
