package com.ciril.test;

import java.nio.file.Path;

import com.ciril.test.config.Configuration;
import com.ciril.test.config.ConfigurationLoader;
import com.ciril.test.simulation.Simulation;
import com.ciril.test.view.DisplayGridConsole;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = ConfigurationLoader.load(Path.of("simulation.properties"));

        Simulation simulation = new Simulation(configuration);

        DisplayGridConsole display = new DisplayGridConsole();

        // État initial
        display.display(simulation.getGrid());

        // On fait avancer le temps tant qu'il reste au moins une case en feu.
        while (simulation.hasFire()) {
            simulation.step();
            System.out.println("simulation step " + simulation.getStepCount() + ":\n");
            display.display(simulation.getGrid());
        }
        
        System.out.println("Simulation terminée en " + simulation.getStepCount() + " étape(s).");
    }
}
