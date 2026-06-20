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
        checkPosition(row, column);
        return cells[row][column];
    }

    public void setState(int row, int column, EtatCase state) {
        checkPosition(row, column);
        cells[row][column] = state;
    }

    public boolean isInsideGrid(int row, int column) {
        return row >= 0 && row < height
                && column >= 0 && column < width;
    }

    private void checkPosition(int row, int column) {
        if (!isInsideGrid(row, column)) {
            throw new IndexOutOfBoundsException(
                    "Position hors de la grille : (" + row + ", " + column + ").");
        }
    }
}
