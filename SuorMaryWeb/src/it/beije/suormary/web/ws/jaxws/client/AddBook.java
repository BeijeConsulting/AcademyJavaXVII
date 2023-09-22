
package it.beije.suormary.web.ws.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per addBook complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="addBook">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="author id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="editor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "addBook", propOrder = {
    "title",
    "description",
    "author0020Id",
    "editor",
    "price",
    "quantity"
})
public class AddBook {

    protected String title;
    protected String description;
    @XmlElement(name = "author id")
    protected int author0020Id;
    protected String editor;
    protected double price;
    protected int quantity;

    /**
     * Recupera il valore della proprietà title.
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
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Recupera il valore della proprietà description.
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
     * Imposta il valore della proprietà description.
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
     * Recupera il valore della proprietà author0020Id.
     * 
     */
    public int getAuthor_0020Id() {
        return author0020Id;
    }

    /**
     * Imposta il valore della proprietà author0020Id.
     * 
     */
    public void setAuthor_0020Id(int value) {
        this.author0020Id = value;
    }

    /**
     * Recupera il valore della proprietà editor.
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
     * Imposta il valore della proprietà editor.
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
     * Recupera il valore della proprietà price.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Imposta il valore della proprietà price.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Recupera il valore della proprietà quantity.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Imposta il valore della proprietà quantity.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

}
