package it.beije.suormary.web.mancuso;

public class ContactDetail {
	private int id;
	private int id_contact;
	private String detail;
	private Character type;
	private String label;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Detail: ").append(getDetail()).append("\nType: ").append(getType()).append("\nLabel: ").append(getLabel()).append("\n");
		return sb.toString();
	}
	
}
