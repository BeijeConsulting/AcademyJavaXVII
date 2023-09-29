package esercizi.esercizio3;


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

	
}
