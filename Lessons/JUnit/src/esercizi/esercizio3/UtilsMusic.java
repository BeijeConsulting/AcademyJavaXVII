package esercizi.esercizio3;

import java.util.ArrayList;
import java.util.List;

public class UtilsMusic {
	
	public Album album = new Album("Soundtracks","Hans Zimmer");
	public List<Songs> songs = new ArrayList<>();
	
	
    public boolean isAlbumFull(Album album) {
        if(album.getAlbumSongs().size() >= album.getMaxSongs()) {
        	 return false;
        } else {
        	return true;
        }
    }

    
    public boolean addSongs(Album album, String titleSong) {
        
    	for(Songs s : album.getAlbumSongs()) {
    		if( s.getTitle() == titleSong) {
    			return false;
    		} else {
    			Songs songs = new Songs(titleSong);
    			album.getAlbumSongs().add(songs);
    		}
    	}
    	return true;
    }

}
