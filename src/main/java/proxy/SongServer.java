package proxy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * REAL SUBJECT — the slow music server that actually holds the song library.
 * Each search starts with the 1-second delay from the assignment,
 * to act like a slow network call. (Only here — NOT in the proxy.)
 */
public class SongServer implements SongService {
    private final Map<Integer, Song> songs = new LinkedHashMap<>();

    public void addSong(Song song) {
        // TODO: put the song in the map, using its id as the key
    }

    @Override
    public Song searchById(Integer songID) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        // TODO: return the song with this id (or null if there isn't one)
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        // TODO: return every song whose title matches, ignoring upper/lower case
        //       (Hint: equalsIgnoreCase)
        return new ArrayList<>();
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        // TODO: return every song whose album matches, ignoring upper/lower case
        return new ArrayList<>();
    }
}
