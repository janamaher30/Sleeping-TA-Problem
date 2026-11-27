package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DashboardPanel extends JPanel {

    public static final String TAS_WORKING = "workingTAs";
    public static final String TAS_SLEEPING = "sleepingTAs";
    public static final String STUDENTS_WAITING = "waitingStudents";
    public static final String STUDENTS_COME_LATER = "studentsReturningLater";

    private JTextField workingField;
    private JTextField sleepingField;
    private JTextField waitingField;
    private JTextField comeLaterField;

    public DashboardPanel() {
        initializePanel();
    }

    private void initializePanel() {
        setBackground(new Color(158, 170, 142));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel title = new JLabel("Output");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 10, 0);
        add(title, c);

        workingField = addRow("#TAs working", 1);
        sleepingField = addRow("#TAs Sleeping", 2);
        waitingField = addRow("#Students Waiting on chairs", 3);
        comeLaterField = addRow("#Students that will come later", 4);
    }

    private JTextField addRow(String text, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = row;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.anchor = GridBagConstraints.WEST;
        add(new JLabel(text), c);

        JTextField field = new JTextField("0", 4);
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBackground(Color.WHITE);
        c.gridx = 1;
        add(field, c);
        return field;
    }

    public void updateStatistics(Map<String, Integer> statistics) {
        if (statistics == null) {
            return;
        }
        final int working = getValue(statistics, TAS_WORKING);
        final int sleeping = getValue(statistics, TAS_SLEEPING);
        final int waiting = getValue(statistics, STUDENTS_WAITING);
        final int comeLater = getValue(statistics, STUDENTS_COME_LATER);

        SwingUtilities.invokeLater(() -> {
            workingField.setText(String.valueOf(working));
            sleepingField.setText(String.valueOf(sleeping));
            waitingField.setText(String.valueOf(waiting));
            comeLaterField.setText(String.valueOf(comeLater));
        });
    }

    private int getValue(Map<String, Integer> map, String key) {
        Integer value = map.get(key);
        if (value == null) {
            return 0;
        }
        return value;
    }
}