package esercizi.esercizio3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class UtilsMusicTest {

    private Album album;
    private UtilsMusic utilsMusic;

    @Before
    public void setUp() {
        album = new Album("Soundtracks", "Hans Zimmer");
        utilsMusic = new UtilsMusic();
    }

    @Test
    public void testIsAlbumFull() {

        assertTrue(utilsMusic.isAlbumFull(album));

        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            assertTrue(utilsMusic.addSongs(album, title));
        }

        assertFalse(utilsMusic.isAlbumFull(album));
    }

    @Test
    public void testAddSongs() {
        assertTrue(utilsMusic.isAlbumFull(album));


        for (int i = 0; i < 5; i++) {
            String title = "Song" + i;
            assertTrue(utilsMusic.addSongs(album, title));
        }

        
        assertFalse(utilsMusic.addSongs(album, "Song0"));


        List<Songs> albumSongs = album.getAlbumSongs();
        assertEquals(5, albumSongs.size());
        for (int i = 0; i < 5; i++) {
            String expectedTitle = "Song" + i;
            assertEquals(expectedTitle, albumSongs.get(i).getTitle());
        }
    }
}
