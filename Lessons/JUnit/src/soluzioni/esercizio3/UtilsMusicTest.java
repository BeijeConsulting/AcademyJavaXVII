package soluzioni.esercizio3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class UtilsMusicTest {

    private Album album;
    private UtilsMusic utilsMusic;

    //ANZICHE' @BeforeClass, che dovendo essere eseguito solo una volta prima di tutto il resto
    //necessiterebbe di essere statico
    //SERVE il before perchè serve che si crei l'oggetto per ogni metodo   
    @Before
    public void setUp() {
        album = new Album("Soundtracks", "Hans Zimmer");
        utilsMusic = new UtilsMusic();
    }

    @Test
    public void testIsAlbumFull() {
    	
    	//ANZICHE' True avendo cambiato i return del metodo
        assertFalse(utilsMusic.isAlbumFull(album));

        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            //ANZICHE' assertFalse: il metodo addSongs ritorna true se aggiunta correttamente
            assertTrue(utilsMusic.addSongs(album, title));
        }
        //ANZICHE' False anvendo cambiato i return del metodo
        assertTrue(utilsMusic.isAlbumFull(album));
    }

    
    @Test
    public void testAddSongs() {
    	//ANZICHE' True avendo cambiato i return del metodo
        assertFalse(utilsMusic.isAlbumFull(album));


        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            assertTrue(utilsMusic.addSongs(album, title));
        }

        
        assertFalse(utilsMusic.addSongs(album, "Song0"));


        List<Songs> albumSongs = album.getAlbumSongs();
        //Serve l'ecquals, controlla il valore non il riferimento
        assertEquals(5, albumSongs.size());
        for (int i = 0; i < 5; i++) {
            String expectedTitle = "Song" + i;
            assertEquals(expectedTitle, albumSongs.get(i).getTitle());
        }
    }
}
