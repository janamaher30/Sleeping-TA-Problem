package app;

import gui.MainFrame;
import javax.swing.SwingUtilities;
import simulation.SimulationController;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationController controller =
                    new SimulationController(10, 2, 3);
            controller.initializeSimulation();
            MainFrame mainFrame = new MainFrame(controller);
        });
    }
}


