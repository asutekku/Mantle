package mantle.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preferences {
    private String filename = "mantle.ini";
    private Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
    private Map<String, String> _entries = new HashMap<>();

    public Preferences(String path) throws IOException {
        load(path);
    }

    public void load(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = _keyValue.matcher(line);
                if (matcher.matches()) {
                    String key = matcher.group(1).trim();
                    String value = matcher.group(2).trim();
                    _entries.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            String line;
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
}
