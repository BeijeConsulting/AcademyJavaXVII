
package it.beije.suormary.web.ws.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per instantBuy complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="instantBuy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Books ids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Books quantities" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="shipping address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instantBuy", propOrder = {
    "books0020Ids",
    "books0020Quantities",
    "user0020Id",
    "shipping0020Address",
    "payment0020Type"
})
public class InstantBuy {

    @XmlElement(name = "Books ids")
    protected String books0020Ids;
    @XmlElement(name = "Books quantities")
    protected String books0020Quantities;
    @XmlElement(name = "user id")
    protected int user0020Id;
    @XmlElement(name = "shipping address")
    protected String shipping0020Address;
    @XmlElement(name = "payment type")
    protected String payment0020Type;

    /**
     * Recupera il valore della proprietà books0020Ids.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBooks_0020Ids() {
        return books0020Ids;
    }

    /**
     * Imposta il valore della proprietà books0020Ids.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBooks_0020Ids(String value) {
        this.books0020Ids = value;
    }

    /**
     * Recupera il valore della proprietà books0020Quantities.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBooks_0020Quantities() {
        return books0020Quantities;
    }

    /**
     * Imposta il valore della proprietà books0020Quantities.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBooks_0020Quantities(String value) {
        this.books0020Quantities = value;
    }

    /**
     * Recupera il valore della proprietà user0020Id.
     * 
     */
    public int getUser_0020Id() {
        return user0020Id;
    }

    /**
     * Imposta il valore della proprietà user0020Id.
     * 
     */
    public void setUser_0020Id(int value) {
        this.user0020Id = value;
    }

    /**
     * Recupera il valore della proprietà shipping0020Address.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipping_0020Address() {
        return shipping0020Address;
    }

    /**
     * Imposta il valore della proprietà shipping0020Address.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipping_0020Address(String value) {
        this.shipping0020Address = value;
    }

    /**
     * Recupera il valore della proprietà payment0020Type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayment_0020Type() {
        return payment0020Type;
    }

    /**
     * Imposta il valore della proprietà payment0020Type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayment_0020Type(String value) {
        this.payment0020Type = value;
    }

}
