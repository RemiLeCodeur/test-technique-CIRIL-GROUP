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
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = ConfigurationLoader.load(Path.of("simulation.properties"));
        
        Simulation simulation = new Simulation(configuration);

        DisplayGridConsole display = new DisplayGridConsole();
        display.display(simulation.getGrid());
    }
}
