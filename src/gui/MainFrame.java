package gui;

import javax.swing.*;
import java.util.Map;

public class MainFrame extends JFrame {
    private DashboardPanel dashboardPanel;
    private StudentPanel studentPanel;
    private TAPanel taPanel;
    private WaitingRoomPanel waitingRoomPanel;
    private ActivityLogPanel activityLogPanel;

    public MainFrame() {
        initializeGUI();
    }

    private void initializeGUI() {
        // TODO: Initialize and arrange GUI components
        setTitle("Sleeping TA Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    public void updateDashboard(Map<String, Integer> statistics) {
        // TODO: Update dashboard
    }

    public void updateStudents() {
        // TODO: Update student panel
    }

    public void updateTAs() {
        // TODO: Update TA panel
    }

    public void updateWaitingRoom() {
        // TODO: Update waiting room panel
    }

    public void addActivityLog(String message) {
        // TODO: Add activity log message
    }
}
