
package it.beije.suormary.bookstore.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per book complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="book">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="author" type="{http://server.bookstore.suormary.beije.it/}author" minOccurs="0"/>
 *         &lt;element name="authorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = {
    "author",
    "authorId",
    "description",
    "editor",
    "id",
    "price",
    "quantity",
    "title"
})
public class Book {

    protected Author author;
    protected int authorId;
    protected String description;
    protected String editor;
    protected int id;
    protected double price;
    protected int quantity;
    protected String title;

    /**
     * Recupera il valore della propriet� author.
     * 
     * @return
     *     possible object is
     *     {@link Author }
     *     
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Imposta il valore della propriet� author.
     * 
     * @param value
     *     allowed object is
     *     {@link Author }
     *     
     */
    public void setAuthor(Author value) {
        this.author = value;
    }

    /**
     * Recupera il valore della propriet� authorId.
     * 
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Imposta il valore della propriet� authorId.
     * 
     */
    public void setAuthorId(int value) {
        this.authorId = value;
    }

    /**
     * Recupera il valore della propriet� description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta il valore della propriet� description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Recupera il valore della propriet� editor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Imposta il valore della propriet� editor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditor(String value) {
        this.editor = value;
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

    /**
     * Recupera il valore della propriet� title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della propriet� title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
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
