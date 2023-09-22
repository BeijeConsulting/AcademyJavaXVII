
package it.beije.suormary.web.ws.jaxws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOrders complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOrders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOrders", propOrder = {
    "user0020Id"
})
public class GetOrders {

    @XmlElement(name = "user id")
    protected int user0020Id;

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

}
