package gui;
import javax.swing.*;
import java.awt.*;
public class ActivityLogPanel extends JPanel {
    private JTextArea activityLog;

    public ActivityLogPanel() {
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        activityLog = new JTextArea();
        activityLog.setEditable(false);
        activityLog.setLineWrap(true);
        activityLog.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(activityLog);
        setBorder(BorderFactory.createTitledBorder(
            "Simulation Activity Log"
        ));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addLogMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return;
        }
        SwingUtilities.invokeLater(() -> {
            activityLog.append(message + "\n");
            activityLog.setCaretPosition(
                activityLog.getDocument().getLength()
            );
        });
    }
}

