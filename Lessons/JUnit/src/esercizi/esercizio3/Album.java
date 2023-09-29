package esercizi.esercizio3;

import java.util.List;

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


}
