package proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PROXY — looks exactly like a SongService, but keeps a cache in memory.
 * The first time something is searched, the proxy asks the real server (slow)
 * and remembers the answer. The same search again comes from the cache (fast).
 */
public class SongServiceProxy implements SongService {
    private final SongService server;
    private final Map<Integer, Song> idCache = new HashMap<>();
    private final Map<String, List<Song>> titleCache = new HashMap<>();
    private final Map<String, List<Song>> albumCache = new HashMap<>();

    public SongServiceProxy(SongService server) {
        this.server = server;
    }

    @Override
    public Song searchById(Integer songID) {
        // TODO: if idCache already has songID, return the cached song.
        //       Otherwise ask the server, store the answer in idCache, and return it.
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        // TODO: same idea as searchById, using titleCache
        return null;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        // TODO: same idea as searchById, using albumCache
        return null;
    }
}
