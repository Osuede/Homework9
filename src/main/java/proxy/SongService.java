package proxy;

import java.util.List;

/**
 * SUBJECT — given in the assignment.
 * Both the real server and the proxy implement this, so the rest of the
 * app can use either one without knowing which it has.
 */
public interface SongService {
    Song searchById(Integer songID);
    List<Song> searchByTitle(String title);
    List<Song> searchByAlbum(String album);
}
