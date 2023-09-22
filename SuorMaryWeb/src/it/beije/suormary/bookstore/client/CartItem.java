
package it.beije.suormary.bookstore.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per cartItem complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="cartItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cartItem", propOrder = {
    "bookId",
    "id",
    "quantity",
    "userId"
})
public class CartItem {

    protected Integer bookId;
    protected int id;
    protected Integer quantity;
    protected Integer userId;

    /**
     * Recupera il valore della propriet� bookId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Imposta il valore della propriet� bookId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBookId(Integer value) {
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
     * Recupera il valore della propriet� quantity.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Imposta il valore della propriet� quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Recupera il valore della propriet� userId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Imposta il valore della propriet� userId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUserId(Integer value) {
        this.userId = value;
    }
    
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", userId : ").append(userId)
				.append(", bookId : ").append(bookId)
				.append(", quantity : ").append(quantity)
				.append(" }");
		return builder.toString();
	}

}
