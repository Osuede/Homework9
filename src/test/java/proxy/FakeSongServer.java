package proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Test helper: a fast fake server (no 1-second delay) that counts how many
 * times each search is called. Lets proxy tests check whether a search went
 * to the server or was answered from the cache.
 */
class FakeSongServer implements SongService {
    static final Song BOHEMIAN_RHAPSODY = new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354);
    static final Song LOVE_OF_MY_LIFE = new Song(2, "Love of My Life", "Queen", "A Night at the Opera", 219);
    static final Song HURT = new Song(3, "Hurt", "Johnny Cash", "American IV", 218);

    private final List<Song> songs = List.of(BOHEMIAN_RHAPSODY, LOVE_OF_MY_LIFE, HURT);

    int idCalls = 0;
    int titleCalls = 0;
    int albumCalls = 0;

    @Override
    public Song searchById(Integer songID) {
        idCalls++;
        for (Song song : songs) {
            if (song.getId() == songID) {
                return song;
            }
        }
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        titleCalls++;
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        albumCalls++;
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAlbum().equals(album)) {
                result.add(song);
            }
        }
        return result;
    }
}
