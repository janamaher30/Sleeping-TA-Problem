import gui.DashboardPanel;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dashboard test");
            DashboardPanel panel = new DashboardPanel();
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            Map<String, Integer> stats = new HashMap<>();
            stats.put(DashboardPanel.TAS_WORKING, 2);
            stats.put(DashboardPanel.TAS_SLEEPING, 0);
            stats.put(DashboardPanel.STUDENTS_WAITING, 3);
            stats.put(DashboardPanel.STUDENTS_COME_LATER, 15);
            panel.updateStatistics(stats);
        });
    }
}
