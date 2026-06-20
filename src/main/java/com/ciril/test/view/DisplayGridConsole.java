package com.ciril.test.view;

import com.ciril.test.model.CellState;
import com.ciril.test.model.Grid;

import java.io.PrintStream;

/**
 * Affiche une {@link Grid} dans la console sous forme de caractères.
 * <p>
 */
public class DisplayGridConsole {

    private final PrintStream out;

    public DisplayGridConsole() {
        this(System.out);
    }

    /** Permet d'injecter un autre flux de sortie si besoin. */
    public DisplayGridConsole(PrintStream out) {
        this.out = out;
    }

    /** Écrit la grille sur le flux de sortie. */
    public void display(Grid grid) {
        out.print(render(grid));
    }

    /** Construit la représentation textuelle de la grille (une ligne par rangée). */
    public String render(Grid grid) {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                builder.append(symbol(grid.getState(row, column)) + " ");
            }
            builder.append(System.lineSeparator());
        }
        builder.append("\n");
        return builder.toString();
    }

    private char symbol(CellState state) {
        return switch (state) {
            case FOREST -> 'T';
            case FIRE -> 'F';
            case BURNT -> '.';
        };
    }
}
