package SmartTaskScheduler;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class TaskSchedulerUI {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();
    private java.util.List<Task> taskList = new ArrayList<>();

    public TaskSchedulerUI() {
        loadTasks();

        JFrame frame = new JFrame("Smart Task Scheduler");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 7, 10, 10));

        JTextField titleField = new JTextField();
        JComboBox<String> priorityBox = new JComboBox<>(new String[] { "HIGH", "MEDIUM", "LOW" });

        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField deadlineField = new JFormattedTextField(dateMask);
        deadlineField.setColumns(10);
        deadlineField.setToolTipText("Enter date as yyyy-mm-dd");

        DefaultListModel<Task> taskListModel = new DefaultListModel<>();
        JList<Task> taskJList = new JList<>(taskListModel);

        JButton addButton = new JButton("Add Task");
        JButton viewButton = new JButton("View Tasks");
        JButton deleteButton = new JButton("Delete Task");
        JButton editButton = new JButton("Edit Task");

        inputPanel.add(new JLabel("Task Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityBox);
        inputPanel.add(new JLabel("Deadline:"));
        inputPanel.add(deadlineField);
        inputPanel.add(addButton);
        inputPanel.add(viewButton);
        inputPanel.add(deleteButton);
        inputPanel.add(editButton);

        String[] filterOptions = { "All Tasks", "Today's Tasks", "High Priority Tasks" };
        JComboBox<String> filterBox = new JComboBox<>(filterOptions);
        inputPanel.add(new JLabel("Filter:"));
        inputPanel.add(filterBox);

        filterBox.addActionListener(e -> {
            String selected = (String) filterBox.getSelectedItem();
            if ("Today's Tasks".equals(selected)) {
                List<Task> filtered = getTodaysTasks();
                updateListModel(filtered, taskListModel);
            } else if ("High Priority Tasks".equals(selected)) {
                List<Task> filtered = getHighPriorityTasks();
                updateListModel(filtered, taskListModel);
            } else {
                refreshList(taskListModel);
            }
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(taskJList);
        scrollPane.setPreferredSize(new Dimension(600, 220));
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String priority = (String) priorityBox.getSelectedItem();
                String dateText = deadlineField.getText();
                if (title.isEmpty() || dateText.contains("_")) {
                    throw new IllegalArgumentException("Please fill in all fields correctly.");
                }

                LocalDate deadline = LocalDate.parse(dateText);
                Task newTask = new Task(title, priority, deadline);
                taskList.add(newTask);
                updateQueue();
                saveTasks();
                JOptionPane.showMessageDialog(frame, "Task Added!");

                titleField.setText("");
                deadlineField.setValue(null);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Use correct date format yyyy-mm-dd.");
            }
        });

        viewButton.addActionListener(e -> {
            loadTasks();
            updateQueue();
            refreshList(taskListModel);
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskList.remove(selectedIndex);
                updateQueue();
                refreshList(taskListModel);
                saveTasks();
            }
        });

        editButton.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            if (selectedIndex != -1) {
                Task selectedTask = taskList.get(selectedIndex);
                titleField.setText(selectedTask.getTitle());
                priorityBox.setSelectedItem(selectedTask.getPriority());
                deadlineField.setText(selectedTask.getDeadline().toString());
                taskList.remove(selectedIndex);
                updateQueue();
                refreshList(taskListModel);
                saveTasks();
            }
        });

        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                LocalDate today = LocalDate.now();
                for (Task task : taskList) {
                    if (task.getDeadline().isEqual(today)) {
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null,
                                "Reminder: Task due today!\n" + task.toString(),
                                "Task Reminder",
                                JOptionPane.WARNING_MESSAGE));
                    }
                }
            }
        }, 0, 60000);

        frame.setVisible(true);
    }

    private void updateQueue() {
        taskQueue.clear();
        taskQueue.addAll(taskList);
        java.util.List<Task> sortedTasks = new ArrayList<>();
        while (!taskQueue.isEmpty()) {
            sortedTasks.add(taskQueue.poll());
        }
        taskList = sortedTasks;
        taskQueue.addAll(taskList);
    }

    private void refreshList(DefaultListModel<Task> model) {
        model.clear();
        for (Task task : taskList) {
            model.addElement(task);
        }
    }

    private void updateListModel(List<Task> tasks, DefaultListModel<Task> model) {
        model.clear();
        for (Task task : tasks) {
            model.addElement(task);
        }
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task task : taskList) {
                writer.write(task.getTitle() + "," + task.getPriority() + "," + task.getDeadline());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        File file = new File("tasks.txt");
        if (!file.exists())
            return;

        taskList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0];
                    String priority = parts[1];
                    LocalDate deadline = LocalDate.parse(parts[2]);

                    Task task = new Task(title, priority, deadline);
                    taskList.add(task);
                }
            }
            updateQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> getTodaysTasks() {
        LocalDate today = LocalDate.now();
        List<Task> todayTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDeadline().equals(today)) {
                todayTasks.add(task);
            }
        }
        return todayTasks;
    }

    private List<Task> getHighPriorityTasks() {
        List<Task> highTasks = new ArrayList<>();
        for (Task task : taskList) {
            if ("HIGH".equalsIgnoreCase(task.getPriority())) {
                highTasks.add(task);
            }
        }
        return highTasks;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskSchedulerUI::new);
    }
}