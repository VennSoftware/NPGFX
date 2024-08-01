package dev.npex42.npgfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {
    private Map<String, String> mValues = new HashMap<>();

    public static Config LoadFile(String filepath) throws RuntimeException {
        try {
            List<String> lines = Files.lines(Paths.get(filepath)).collect(Collectors.toUnmodifiableList());
            Config conf = new Config();

            for (String line : lines) {
                if (line.startsWith("#")) continue; // Comment

                String[] parts = line.split("\\s*=\\s*");
                if (parts.length < 2) {
                    System.out.printf("[!] Malformed Config Item: '%s' %n", line);
                    continue; // Skip
                }
                conf.mValues.put(parts[0], parts[1]);

            }
            return conf;

        } catch (IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }


    public int GetIntOr(String key, int def) {
        if (mValues.containsKey(key)) {
            return Integer.parseInt(mValues.get(key));
        } else {
            return def;
        }
    }

    public String GetStringOr(String key, String def) {
        return mValues.getOrDefault(key, def);
    }

    public double GetDoubleOr(String key, double def) {
        if (mValues.containsKey(key)) {
            return Double.parseDouble(mValues.get(key));
        } else {
            return def;
        }
    }
}
