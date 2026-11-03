package proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!idCache.containsKey(songID)) {
            idCache.put(songID, server.searchById(songID));
        }
        return idCache.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        if (!titleCache.containsKey(title)) {
            titleCache.put(title, server.searchByTitle(title));
        }
        return titleCache.get(title);
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        if (!albumCache.containsKey(album)) {
            albumCache.put(album, server.searchByAlbum(album));
        }
        return albumCache.get(album);
    }
}
