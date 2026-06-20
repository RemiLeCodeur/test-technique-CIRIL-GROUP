package com.ciril.test.config;

import com.ciril.test.model.Position;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Charge une {@link Configuration} depuis un fichier de propriétés (.properties).
 * <p>
 * Seule cette classe touche au système de fichiers : la lecture (I/O)
 */
public final class ConfigurationLoader {

    private static final String KEY_HEIGHT = "grid.height";
    private static final String KEY_WIDTH = "grid.width";
    private static final String KEY_PROBABILITY = "fire.propagation.probability";
    private static final String KEY_INITIAL_FIRE = "fire.initial";

    private ConfigurationLoader() {
    }

    public static Configuration load(Path path) {
        try (InputStream in = Files.newInputStream(path)) {
            Properties properties = new Properties();
            properties.load(in);
            return fromProperties(properties);
        } catch (IOException e) {
            throw new UncheckedIOException("Impossible de lire le fichier de configuration : " + path, e);
        }
    }

    static Configuration fromProperties(Properties properties) {
        int height = parseInt(properties, KEY_HEIGHT);
        int width = parseInt(properties, KEY_WIDTH);
        double probability = parseDouble(properties, KEY_PROBABILITY);
        List<Position> initialFire = parsePositions(properties, KEY_INITIAL_FIRE);
        return new Configuration(height, width, probability, initialFire);
    }

    private static String require(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Paramètre manquant dans la configuration : " + key);
        }
        return value.trim();
    }

    private static int parseInt(Properties properties, String key) {
        String value = require(properties, key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Le paramètre '" + key + "' doit être un entier (reçu : " + value + ").");
        }
    }

    private static double parseDouble(Properties properties, String key) {
        String value = require(properties, key);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Le paramètre '" + key + "' doit être un nombre décimal (reçu : " + value + ").");
        }
    }

    /** Format attendu : {@code row,column} séparés par ';'  (ex : {@code 0,0;5,5}). */
    private static List<Position> parsePositions(Properties properties, String key) {
        String value = require(properties, key);
        List<Position> positions = new ArrayList<>();
        for (String token : value.split(";")) {
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            String[] parts = trimmed.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "Position mal formée dans '" + key + "' : '" + trimmed + "' (attendu 'row,column').");
            }
            try {
                int row = Integer.parseInt(parts[0].trim());
                int column = Integer.parseInt(parts[1].trim());
                positions.add(new Position(row, column));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Coordonnées non entières dans '" + key + "' : '" + trimmed + "'.");
            }
        }
        return positions;
    }
}
