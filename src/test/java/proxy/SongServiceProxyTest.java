package proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;

class SongServiceProxyTest {

    private final FakeSongServer server = new FakeSongServer();
    private final SongService proxy = new SongServiceProxy(server);

    // searchById

    @Test
    void firstSearchByIdAsksTheServer() {
        Song song = proxy.searchById(1);
        assertEquals(1, server.idCalls, "the first search should go to the server");
        assertSame(FakeSongServer.BOHEMIAN_RHAPSODY, song);
    }

    @Test
    void repeatedSearchByIdUsesTheCache() {
        proxy.searchById(1);
        Song again = proxy.searchById(1);
        assertEquals(1, server.idCalls, "the second search should come from the cache, not the server");
        assertSame(FakeSongServer.BOHEMIAN_RHAPSODY, again);
    }

    @Test
    void eachDifferentIdAsksTheServerOnlyOnce() {
        proxy.searchById(1);
        proxy.searchById(3);
        proxy.searchById(1);
        proxy.searchById(3);
        assertEquals(2, server.idCalls, "2 different ids should mean only 2 trips to the server");
    }

    // searchByTitle

    @Test
    void firstSearchByTitleAsksTheServer() {
        List<Song> songs = proxy.searchByTitle("Hurt");
        assertEquals(1, server.titleCalls, "the first search should go to the server");
        assertEquals(List.of(FakeSongServer.HURT), songs);
    }

    @Test
    void repeatedSearchByTitleUsesTheCache() {
        proxy.searchByTitle("Hurt");
        proxy.searchByTitle("Hurt");
        assertEquals(1, server.titleCalls, "the second search should come from the cache, not the server");
    }

    // searchByAlbum

    @Test
    void firstSearchByAlbumAsksTheServer() {
        List<Song> songs = proxy.searchByAlbum("A Night at the Opera");
        assertEquals(1, server.albumCalls, "the first search should go to the server");
        assertEquals(List.of(FakeSongServer.BOHEMIAN_RHAPSODY, FakeSongServer.LOVE_OF_MY_LIFE), songs);
    }

    @Test
    void repeatedSearchByAlbumUsesTheCache() {
        proxy.searchByAlbum("A Night at the Opera");
        proxy.searchByAlbum("A Night at the Opera");
        assertEquals(1, server.albumCalls, "the second search should come from the cache, not the server");
    }
}
