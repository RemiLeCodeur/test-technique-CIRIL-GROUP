package com.ciril.test.simulation;

import com.ciril.test.config.Configuration;
import com.ciril.test.model.EtatCase;
import com.ciril.test.model.Grille;
import com.ciril.test.model.Position;

import java.util.Random;

/**
 * Orchestre la simulation : porte la grille courante, la probabilité de
 * propagation et la source d'aléa, et fait avancer le temps étape par étape.
 */
public class Simulation {

    private final double propagationProbability;
    private final Random random;
    private Grille grid;
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

    private static Grille buildInitialGrid(Configuration configuration) {
        Grille initialGrid = new Grille(configuration.getHeight(), configuration.getWidth());
        for (Position position : configuration.getInitialFire()) {
            initialGrid.setState(position.getRow(), position.getColumn(), EtatCase.FIRE);
        }
        return initialGrid;
    }

    /**
     * Fait avancer la simulation d'une étape (t -> t+1). À implémenter.
     */
    public void step() {
        // TODO
    }

    /** Indique s'il reste au moins une case en feu (condition d'arrêt de la simulation). */
    public boolean hasFire() {
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                if (grid.getState(row, column) == EtatCase.FIRE) {
                    return true;
                }
            }
        }
        return false;
    }

    public Grille getGrid() {
        return grid;
    }

    public int getStepCount() {
        return stepCount;
    }
}
