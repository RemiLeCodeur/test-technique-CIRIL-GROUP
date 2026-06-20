package com.ciril.test.config;

import com.ciril.test.model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Paramètres immuables d'une simulation, validés à la construction.
 * Cette classe ne connaît rien aux fichiers : c'est un simple porteur de données.
 */
public final class Configuration {

    private final int height;
    private final int width;
    private final double propagationProbability;
    private final List<Position> initialFire;

    public Configuration(int height, int width, double propagationProbability, List<Position> initialFire) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException(
                    "Les dimensions doivent être strictement positives (reçu : " + height + "x" + width + ").");
        }
        if (propagationProbability < 0.0 || propagationProbability > 1.0) {
            throw new IllegalArgumentException(
                    "La probabilité doit être comprise entre 0 et 1 (reçu : " + propagationProbability + ").");
        }
        if (initialFire == null || initialFire.isEmpty()) {
            throw new IllegalArgumentException("Au moins une case initialement en feu est requise.");
        }
        for (Position position : initialFire) {
            if (position.getRow() < 0 || position.getRow() >= height
                    || position.getColumn() < 0 || position.getColumn() >= width) {
                throw new IllegalArgumentException("Case en feu hors de la grille : " + position + ".");
            }
        }
        this.height = height;
        this.width = width;
        this.propagationProbability = propagationProbability;
        this.initialFire = Collections.unmodifiableList(new ArrayList<>(initialFire));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public List<Position> getInitialFire() {
        return initialFire;
    }
}
