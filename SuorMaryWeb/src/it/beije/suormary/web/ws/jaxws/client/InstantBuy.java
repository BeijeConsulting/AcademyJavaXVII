
package it.beije.suormary.web.ws.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="booksids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="booksquantities" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="shippingaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymenttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "booksids",
    "booksquantities",
    "userid",
    "shippingaddress",
    "paymenttype"
})
public class InstantBuy {

    protected String booksids;
    protected String booksquantities;
    protected int userid;
    protected String shippingaddress;
    protected String paymenttype;

    /**
     * Recupera il valore della proprietà booksids.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBooksids() {
        return booksids;
    }

    /**
     * Imposta il valore della proprietà booksids.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBooksids(String value) {
        this.booksids = value;
    }

    /**
     * Recupera il valore della proprietà booksquantities.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBooksquantities() {
        return booksquantities;
    }

    /**
     * Imposta il valore della proprietà booksquantities.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBooksquantities(String value) {
        this.booksquantities = value;
    }

    /**
     * Recupera il valore della proprietà userid.
     * 
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Imposta il valore della proprietà userid.
     * 
     */
    public void setUserid(int value) {
        this.userid = value;
    }

    /**
     * Recupera il valore della proprietà shippingaddress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingaddress() {
        return shippingaddress;
    }

    /**
     * Imposta il valore della proprietà shippingaddress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingaddress(String value) {
        this.shippingaddress = value;
    }

    /**
     * Recupera il valore della proprietà paymenttype.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymenttype() {
        return paymenttype;
    }

    /**
     * Imposta il valore della proprietà paymenttype.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymenttype(String value) {
        this.paymenttype = value;
    }

}
