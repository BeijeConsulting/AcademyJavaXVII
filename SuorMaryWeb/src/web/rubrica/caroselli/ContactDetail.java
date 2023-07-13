package web.rubrica.caroselli;
//si riferisce alla tabella riferimento

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
public class ContactDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "id_rubrica")
	private int id_contact;

	@Column(name = "contact")
	private String detail;

	@Column(name = "type")
	private Character type;

	@Column(name = "label")
	private String label;


	public ContactDetail() {

	}



	public ContactDetail(String detail, Character string, String label) {
		super();
		this.detail = detail;
		this.type = string;
		this.label = label;
	}



	public int getId() {
		return id;
	}

	public int getId_contact() {
		return id_contact;
	}

	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ").append("id : ").append(id).append(", id_contact : ")
				.append(id_contact).append(", detail : ").append(detail).append(", type : ").append(type)
				.append(", label : ").append(label).append(" }");

		return builder.toString();
	}

}
