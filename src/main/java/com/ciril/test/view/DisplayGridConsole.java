package com.ciril.test.view;

import com.ciril.test.model.EtatCase;
import com.ciril.test.model.Grille;

import java.io.PrintStream;

/**
 * Affiche une {@link Grille} dans la console sous forme de caractères.
 * <p>
 * La conversion grille -> texte ({@link #render}) est séparée de l'écriture
 * ({@link #display}) : on peut ainsi tester le rendu sans rien imprimer.
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
    public void display(Grille grid) {
        out.print(render(grid));
    }

    /** Construit la représentation textuelle de la grille (une ligne par rangée). */
    public String render(Grille grid) {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                builder.append(symbol(grid.getState(row, column)) + " ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private char symbol(EtatCase state) {
        return switch (state) {
            case FOREST -> 'T';
            case FIRE -> 'F';
            case BURNT -> '.';
        };
    }
}
