package proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** These tests are slow on purpose: every real server search waits 1 second. */
class SongServerTest {

    private final Song bohemianRhapsody = new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354);
    private final Song loveOfMyLife = new Song(2, "Love of My Life", "Queen", "A Night at the Opera", 219);
    private final Song hurtCash = new Song(3, "Hurt", "Johnny Cash", "American IV", 218);
    private final Song hurtNin = new Song(4, "Hurt", "Nine Inch Nails", "The Downward Spiral", 373);
    private final SongServer server = new SongServer();

    @BeforeEach
    void addSongs() {
        server.addSong(bohemianRhapsody);
        server.addSong(loveOfMyLife);
        server.addSong(hurtCash);
        server.addSong(hurtNin);
    }

    // addSong

    @Test
    void addSongMakesTheSongSearchable() {
        Song yesterday = new Song(5, "Yesterday", "The Beatles", "Help!", 125);
        server.addSong(yesterday);
        assertSame(yesterday, server.searchById(5));
    }

    // searchById

    @Test
    void searchByIdFindsTheSong() {
        assertSame(hurtCash, server.searchById(3));
    }

    @Test
    void searchByIdReturnsNullForAnUnknownId() {
        assertNull(server.searchById(99));
    }

    // searchByTitle

    @Test
    void searchByTitleFindsEverySongWithThatTitle() {
        assertEquals(List.of(hurtCash, hurtNin), server.searchByTitle("Hurt"));
    }

    @Test
    void searchByTitleReturnsEmptyListWhenNothingMatches() {
        assertTrue(server.searchByTitle("Thriller").isEmpty());
    }

    // searchByAlbum

    @Test
    void searchByAlbumFindsEverySongOnTheAlbumIgnoringCase() {
        assertEquals(List.of(bohemianRhapsody, loveOfMyLife), server.searchByAlbum("a night at the opera"));
    }
}
