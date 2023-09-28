package soluzioni.esercizio3;

import java.util.Objects;

public class Songs {
	
	private String title;
	private String author;
	private String albumTitle;
	
	
	
	public Songs() {
		super();
	}

	public Songs(String title) {
		super();
		this.title = title;
		this.author = "Hanz Zimmer";
		this.albumTitle = "Soundtracks";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAlbumTitle() {
		return albumTitle;
	}
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	//INSERIRE OVERRIDE EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Songs other = (Songs) obj;
		return Objects.equals(albumTitle, other.albumTitle) && Objects.equals(author, other.author)
				&& Objects.equals(title, other.title);
	}


	
	
	
	
}
