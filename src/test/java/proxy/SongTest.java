package proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SongTest {

    private final Song song = new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354);

    // getId

    @Test
    void getIdReturnsTheId() {
        assertEquals(1, song.getId());
    }

    // getTitle

    @Test
    void getTitleReturnsTheTitle() {
        assertEquals("Bohemian Rhapsody", song.getTitle());
    }

    // getArtist

    @Test
    void getArtistReturnsTheArtist() {
        assertEquals("Queen", song.getArtist());
    }

    // getAlbum

    @Test
    void getAlbumReturnsTheAlbum() {
        assertEquals("A Night at the Opera", song.getAlbum());
    }

    // getDuration

    @Test
    void getDurationReturnsTheLengthInSeconds() {
        assertEquals(354, song.getDuration());
    }

    // toString

    @Test
    void toStringShowsTitleAndArtist() {
        String text = song.toString();
        assertTrue(text.contains("Bohemian Rhapsody") && text.contains("Queen"),
                "toString should include the title and artist");
    }
}
