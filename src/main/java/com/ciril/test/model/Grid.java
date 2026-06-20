package com.ciril.test.model;

import java.util.Arrays;

/**
 * Grille 2D représentant la forêt, de dimension height x width.
 * Toutes les cases sont initialisées à FOREST.
 */
public class Grid {

    private final int height;
    private final int width;
    private final CellState[][] cells;

    public Grid(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException(
                    "Les dimensions doivent être strictement positives (reçu : "
                            + height + "x" + width + ").");
        }
        this.height = height;
        this.width = width;
        this.cells = new CellState[height][width];
        for (CellState[] row : cells) {
            Arrays.fill(row, CellState.FOREST);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Retourne l'état de la case (row, column).
     */
    public CellState getState(int row, int column) {
        requireInsideGrid(row, column);
        return cells[row][column];
    }

    /**
     * Définit l'état de la case (row, column).
     */
    public void setState(int row, int column, CellState state) {
        requireInsideGrid(row, column);
        cells[row][column] = state;
    }

    public boolean isInsideGrid(int row, int column) {
        return row >= 0 && row < height
                && column >= 0 && column < width;
    }

    private void requireInsideGrid(int row, int column) {
        if (!isInsideGrid(row, column)) {
            throw new IndexOutOfBoundsException(
                    "Coordonnées hors de la grille " + height + "x" + width
                            + " : (" + row + ", " + column + ").");
        }
    }

    /**
     * Crée une copie indépendante de la grille (même dimensions et mêmes états).
     */
    public Grid copyOf() {
        Grid copy = new Grid(this.getHeight(), this.getWidth());
        for (int row = 0; row < this.getHeight(); row++) {
            for (int column = 0; column < this.getWidth(); column++) {
                copy.setState(row, column, this.getState(row, column));
            }
        }
        return copy;
    }
}
