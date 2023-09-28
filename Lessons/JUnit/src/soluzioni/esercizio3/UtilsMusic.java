package soluzioni.esercizio3;

import java.util.ArrayList;
import java.util.List;

public class UtilsMusic {
	
	public Album album = new Album("Soundtracks","Hans Zimmer");
	public List<Songs> songs = new ArrayList<>();
	
	//si devono invertire i return, deve dare falso se la condizione NON è vera
    public boolean isAlbumFull(Album album) {
        if(album.getAlbumSongs().size() >= album.getMaxSongs()) {
        	 return true;
        } else {
        	return false;
        }
    }

    
    public boolean addSongs(Album album, String titleSong) {
        
    	for(Songs s : album.getAlbumSongs()) {
    		//non == ma equals, si parla di stringhe
    		if( s.getTitle().equals(titleSong)) {
    			return false;
    			} 
    	}
    		
    	//FUORI DAL CICLO, se inserito all'interno non aggiungerà correttamente la canzone,
    	//aggiungendo una nuova canzone per ogni canzone con titolo diverso
    	Songs songs = new Songs(titleSong);
    	album.getAlbumSongs().add(songs);
    		
    	return true;
    }

}
