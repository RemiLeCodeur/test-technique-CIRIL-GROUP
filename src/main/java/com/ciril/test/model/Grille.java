package com.ciril.test.model;

import java.util.Arrays;

/**
 * Grille 2D représentant la forêt, de dimension height x width.
 * Toutes les cases sont initialisées à FOREST.
 */
public class Grille {

    private final int height;
    private final int width;
    private final EtatCase[][] cells;

    public Grille(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException(
                    "Les dimensions doivent être strictement positives (reçu : "
                            + height + "x" + width + ").");
        }
        this.height = height;
        this.width = width;
        this.cells = new EtatCase[height][width];
        for (EtatCase[] row : cells) {
            Arrays.fill(row, EtatCase.FOREST);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public EtatCase getState(int row, int column) {
        if (isInsideGrid(row, column)) {
            return cells[row][column];
        }
        return null;
    }

    public void setState(int row, int column, EtatCase state) {
        if (isInsideGrid(row, column)) { // Ne rien faire si les coordonnées ne sont pas bonnes
            cells[row][column] = state;
        }
    }

    public boolean isInsideGrid(int row, int column) {
        return row >= 0 && row < height
                && column >= 0 && column < width;
    }

    /**
     * Crée une copie indépendante de la grille (même dimensions et mêmes états).
     */
    public Grille copyOf() {
        Grille copy = new Grille(this.getHeight(), this.getWidth());
        for (int row = 0; row < this.getHeight(); row++) {
            for (int column = 0; column < this.getWidth(); column++) {
                copy.setState(row, column, this.getState(row, column));
            }
        }
        return copy;
    }
}
