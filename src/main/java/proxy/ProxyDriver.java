package proxy;

import java.util.List;

public class ProxyDriver {
    public static void main(String[] args) {
        SongServer server = new SongServer();
        server.addSong(new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354));
        server.addSong(new Song(2, "Love of My Life", "Queen", "A Night at the Opera", 219));
        server.addSong(new Song(3, "Hurt", "Johnny Cash", "American IV", 218));
        server.addSong(new Song(4, "Hurt", "Nine Inch Nails", "The Downward Spiral", 373));
        server.addSong(new Song(5, "Billie Jean", "Michael Jackson", "Thriller", 294));
        server.addSong(new Song(6, "Thriller", "Michael Jackson", "Thriller", 357));
        server.addSong(new Song(7, "Yesterday", "The Beatles", "Help!", 125));

        SongService service = new SongServiceProxy(server);

        System.out.println("===== searchById(5) =====");
        for (int i = 1; i <= 2; i++) {
            long start = System.currentTimeMillis();
            Song song = service.searchById(5);
            long time = System.currentTimeMillis() - start;
            System.out.println("Search " + i + " took " + time + " ms: " + song);
        }

        System.out.println();
        System.out.println("===== searchByTitle(\"Hurt\") =====");
        for (int i = 1; i <= 2; i++) {
            long start = System.currentTimeMillis();
            List<Song> songs = service.searchByTitle("Hurt");
            long time = System.currentTimeMillis() - start;
            System.out.println("Search " + i + " took " + time + " ms: " + songs);
        }

        System.out.println();
        System.out.println("===== searchByAlbum(\"Thriller\") =====");
        for (int i = 1; i <= 2; i++) {
            long start = System.currentTimeMillis();
            List<Song> songs = service.searchByAlbum("Thriller");
            long time = System.currentTimeMillis() - start;
            System.out.println("Search " + i + " took " + time + " ms: " + songs);
        }
    }
}
