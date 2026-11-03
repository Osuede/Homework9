package flyweight;

import java.util.HashMap;
import java.util.Map;

public class CharacterPropertiesFactory {
    private final Map<String, CharacterProperties> cache = new HashMap<>();

    public CharacterProperties getProperties(String font, int size, String color) {
        String key = font + "-" + size + "-" + color;
        if (!cache.containsKey(key)) {
            cache.put(key, new CharacterProperties(font, size, color));
        }
        return cache.get(key);
    }

    public int getCount() {
        return cache.size();
    }
}
