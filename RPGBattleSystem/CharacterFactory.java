/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Isaiah Aragon
 */
public class CharacterFactory {

    private static final Map<String, java.util.function.Function<String, Character>> registry = new HashMap<>();

    // Register method
    public static void register(String name, java.util.function.Function<String, Character> constructor) {
        registry.put(name, constructor);
    }

    // Create character
    public static Character create(String type, String name) {
        if (!registry.containsKey(type)) {
            throw new IllegalArgumentException("Unknown character: " + type);
        }
        return registry.get(type).apply(name);
    }

    // Get all available characters
    public static String[] getAvailableCharacters() {
        return registry.keySet().toArray(new String[0]);
    }
}
