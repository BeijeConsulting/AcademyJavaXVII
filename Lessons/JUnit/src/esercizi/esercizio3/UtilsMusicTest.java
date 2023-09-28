package esercizi.esercizio3;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

//sorry not sorry
public class UtilsMusicTest {

    private Album album;
    private UtilsMusic utilsMusic;

    @BeforeClass
    public void setUp() {
        album = new Album("Soundtracks", "Hans Zimmer");
        utilsMusic = new UtilsMusic();
    }
    
    //Controllo se che l'album sia vuoto e lo popolo, poi controllo che sia popolato correttamente
    @Test
    public void testIsAlbumFull() {

        assertTrue(utilsMusic.isAlbumFull(album));

        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            assertFalse(utilsMusic.addSongs(album, title));
        }
      
        assertFalse(utilsMusic.isAlbumFull(album));
    }

    
    //Controllo se che l'album sia vuoto e lo popolo, poi provo ad aggiungere una canzone già presente
    @Test
    public void testAddSongs() {
        assertTrue(utilsMusic.isAlbumFull(album));


        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            assertTrue(utilsMusic.addSongs(album, title));
        }

        
        assertFalse(utilsMusic.addSongs(album, "Song0"));


        List<Songs> albumSongs = album.getAlbumSongs();
        assertSame(5, albumSongs.size());
        for (int i = 0; i < 5; i++) {
            String expectedTitle = "Song" + i;
            assertEquals(expectedTitle, albumSongs.get(i).getTitle());
        }
    }
}
