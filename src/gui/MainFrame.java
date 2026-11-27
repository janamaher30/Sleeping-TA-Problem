package gui;

import javax.swing.*;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import simulation.SimulationController;
import core.SharedState;

public class MainFrame extends JFrame {
    private DashboardPanel dashboardPanel;
    private StudentPanel studentPanel;
    private TAPanel taPanel;
    private WaitingRoomPanel waitingRoomPanel;
    private ActivityLogPanel activityLogPanel;
    private SimulationController simulationController;
    private Timer updateTimer;

    public MainFrame(SimulationController simulationController) {
        this.simulationController = simulationController;
        initializeGUI();
        initializeUpdateTimer();
    }
    
    private void initializeGUI() {
        setTitle("Sleeping TA Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        dashboardPanel = new DashboardPanel();
        studentPanel = new StudentPanel();
        taPanel = new TAPanel();
        waitingRoomPanel = new WaitingRoomPanel();
        activityLogPanel = new ActivityLogPanel();
        setLayout(new BorderLayout(10, 10));
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.add(taPanel);
        centerPanel.add(waitingRoomPanel);
        add(dashboardPanel, BorderLayout.NORTH);
        add(studentPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(activityLogPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (updateTimer != null) {
                    updateTimer.stop();
                }
            }
        });
        setVisible(true);
    }

    private void initializeUpdateTimer() {
        updateTimer = new Timer(500, e -> refreshGUI());
        updateTimer.start();
    }

    private void refreshGUI() {
        if (simulationController.getSharedState() == null) {
            return;
        }
        updateDashboard(
                simulationController.getSharedState().getStatistics()
        );
        updateStudents();
        updateTAs();
        updateWaitingRoom();
    }

    public void updateDashboard(Map<String, Integer> statistics) {
        dashboardPanel.updateStatistics(statistics);
    }

    public void updateStudents() {
        Map<Integer, SharedState.StudentStatus> studentStatuses =
                simulationController.getSharedState().getStudentStatuses();
        studentPanel.updateStudents(studentStatuses);
    }

    public void updateTAs() {
        Map<Integer, SharedState.TAStatus> taStatuses =
                simulationController.getSharedState().getTAStatuses();
        taPanel.updateTAs(taStatuses);
    }

    public void updateWaitingRoom() {
        int waitingStudents =
                simulationController.getWaitingRoom().getWaitingCount();
        int availableChairs =
                simulationController.getWaitingRoom().getAvailableChairs();
        waitingRoomPanel.updateWaitingRoom(
                waitingStudents,
                availableChairs
        );
    }

    public void addActivityLog(String message) {

        activityLogPanel.addLogMessage(message);
    }
}

