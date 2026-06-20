package com.ciril.test.simulation;

import com.ciril.test.config.Configuration;
import com.ciril.test.model.CellState;
import com.ciril.test.model.Grid;
import com.ciril.test.model.Position;

import java.util.Random;

/**
 * Orchestre la simulation : porte la grille courante, la probabilité de
 * propagation et la source d'aléa, et fait avancer le temps étape par étape.
 */
public class Simulation {

    private final double propagationProbability;
    private final Random random;
    private Grid grid;
    private int stepCount;

    public Simulation(Configuration configuration) {
        this(configuration, new Random());
    }

    /** Constructeur permettant d'injecter un {@link Random} (utile pour des tests déterministes). */
    public Simulation(Configuration configuration, Random random) {
        this.propagationProbability = configuration.getPropagationProbability();
        this.random = random;
        this.grid = buildInitialGrid(configuration);
        this.stepCount = 0;
    }

    private static Grid buildInitialGrid(Configuration configuration) {
        Grid initialGrid = new Grid(configuration.getHeight(), configuration.getWidth());
        for (Position position : configuration.getInitialFire()) {
            initialGrid.setState(position.getRow(), position.getColumn(), CellState.FIRE);
        }
        return initialGrid;
    }

    /**
     * Fait avancer la simulation d'une étape (t -> t+1). À implémenter.
     * Le feu s'éteint dans cette case (la case est remplie de cendre et ne peut
     * ensuite plus brûler)
     * et il y a une probabilité p que le feu se propage à chacune des 4 cases
     * adjacentes
     */
    public void step() {
        Grid newGrid = this.grid.copyOf();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                if (grid.getState(row, column) == CellState.FIRE) {
                    // Le feu s'éteint : la case devient cendre et ne brûlera plus.
                    newGrid.setState(row, column, CellState.BURNT);

                    // Tentative de propagation aux 4 cases adjacentes.
                    propagateTo(newGrid, row - 1, column);
                    propagateTo(newGrid, row + 1, column);
                    propagateTo(newGrid, row, column - 1);
                    propagateTo(newGrid, row, column + 1);
                }
            }
        }
        this.grid = newGrid;
        this.stepCount++;
    }

    /**
     * Tente d'enflammer la case (row, column). La case peut être hors grille
     * (voisin d'une case en bordure) : on vérifie donc les bornes avant tout accès.
     */
    private void propagateTo(Grid newGrid, int row, int column) {
        if (grid.isInsideGrid(row, column)
                && grid.getState(row, column) == CellState.FOREST
                && random.nextDouble() < propagationProbability) {
            newGrid.setState(row, column, CellState.FIRE);
        }
    }

    /** Indique s'il reste au moins une case en feu (condition d'arrêt de la simulation). */
    public boolean hasFire() {
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                if (grid.getState(row, column) == CellState.FIRE) {
                    return true;
                }
            }
        }
        return false;
    }

    public Grid getGrid() {
        return grid;
    }

    public int getStepCount() {
        return stepCount;
    }
}
