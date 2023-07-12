package it.beije.suormary.web.Char;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="riferimento")
public class ContactDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int id_rubrica;
	@Column
	private String contatto;
	@Column
	private Character tipo;
	@Column
	private String label;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_rubrica() {
		return id_rubrica;
	}
	public void setId_rubrica(int id_rubrica) {
		this.id_rubrica = id_rubrica;
	}
	public String getContatto() {
		return contatto;
	}
	public void setContatto(String contatto) {
		this.contatto = contatto;
	}
	public Character getTipo() {
		return tipo;
	}
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}


}
