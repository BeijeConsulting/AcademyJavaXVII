package soluzioni.esercizio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
	
	private String title;
	private String author;
	private List<Songs> albumSongs;
	private int maxSongs = 5;
	
	public Album() {
		super();
	}

	public Album(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		//MANCAVA inizializzazione della lista di canzoni
		this.albumSongs = new ArrayList<>();
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
	
	public List<Songs> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(List<Songs> albumSongs) {
		this.albumSongs = albumSongs;
	}

	public int getMaxSongs() {
		return maxSongs;
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
		Album other = (Album) obj;
		return Objects.equals(albumSongs, other.albumSongs) && Objects.equals(author, other.author)
				&& maxSongs == other.maxSongs && Objects.equals(title, other.title);
	}
	
	

}
