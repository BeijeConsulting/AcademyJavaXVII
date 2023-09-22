
package it.beije.suormary.bookstore.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per user complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creationDate" type="{http://server.bookstore.suormary.beije.it/}localDateTime" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "creationDate",
    "email",
    "id",
    "name",
    "password",
    "surname"
})
public class User {

    protected LocalDateTime creationDate;
    protected String email;
    protected int id;
    protected String name;
    protected String password;
    protected String surname;

    /**
     * Recupera il valore della propriet� creationDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Imposta il valore della propriet� creationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setCreationDate(LocalDateTime value) {
        this.creationDate = value;
    }

    /**
     * Recupera il valore della propriet� email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della propriet� email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Recupera il valore della propriet� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della propriet� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della propriet� password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta il valore della propriet� password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Recupera il valore della propriet� surname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Imposta il valore della propriet� surname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }
    
    public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", email : ").append(email)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
				.append(", creationDate : ").append(creationDate)
				.append(" }");
		
		return builder.toString();
	}	
    
}
