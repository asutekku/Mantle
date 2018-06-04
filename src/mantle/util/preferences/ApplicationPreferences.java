package mantle.util.preferences;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author akko
 * @version 5 Apr 2018
 *
 */
public class ApplicationPreferences {

    /**
     * Mantle Preference fileHelper = .mpf
     * Looks like ini
     * Behaves like ini
     * Smells like ini
     *
     * But is mpf
     */
    private static String filename = "mantle.mpf";
    private static Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
    private static Map<String, String> _entries = new HashMap<>();

    /**
     * @param path Path to load application preferences
     */
    public ApplicationPreferences(String path) {
        load(path);
    }

    /**
     * @param path Path to load from
     */
    public static void load(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = _keyValue.matcher(line);
                if (matcher.matches()) {
                    String key = matcher.group(1).trim();
                    String value = matcher.group(2).trim();
                    System.out.println("Map contains: " + key + " and " + value);
                    _entries.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path Path to save to
     */
    public static void save(String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<String, String> entry : _entries.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                bufferedWriter.write(key + "=" + value);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing function
     * 
     * @param args none
     */
    public static void main(String[] args){
        load(filename);
        save(filename);
    }
}
