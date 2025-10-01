import model.Student;
import service.StudentService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProjectUI {  

    private StudentService service;
    private JFrame frame;
    private JTextField idField, nameField, ageField, marksField;
    private JTable table;
    private DefaultTableModel model;

    public ProjectUI() {
        service = new StudentService();
        frame = new JFrame("Student Management System");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        idField = new JTextField(); idField.setEditable(false);
        nameField = new JTextField(); ageField = new JTextField(); marksField = new JTextField();

        inputPanel.add(new JLabel("ID:")); inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:")); inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:")); inputPanel.add(ageField);
        inputPanel.add(new JLabel("Marks:")); inputPanel.add(marksField);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton sortNameBtn = new JButton("Sort by Name");
        JButton sortMarksBtn = new JButton("Sort by Marks");

        inputPanel.add(addBtn); inputPanel.add(updateBtn);
        inputPanel.add(deleteBtn); inputPanel.add(sortNameBtn);
        inputPanel.add(sortMarksBtn);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[]{"ID","Name","Age","Marks"},0);
        table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // Load Data
        loadTable();

        // Button actions
        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        sortNameBtn.addActionListener(e -> sortByName());
        sortMarksBtn.addActionListener(e -> sortByMarks());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                idField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
                nameField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
                ageField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
                marksField.setText(table.getValueAt(table.getSelectedRow(),3).toString());
            }
        });

        frame.setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        for (Student s : service.getAllStudents()) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getMarks()});
        }
    }

    private void addStudent() {
        try {
            if(nameField.getText().isEmpty() || ageField.getText().isEmpty() || marksField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,"Please fill all fields!");
                return;
            }
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double marks = Double.parseDouble(marksField.getText());
            service.addStudent(name, age, marks);
            loadTable();
            JOptionPane.showMessageDialog(frame,"Student added!");

            nameField.setText("");
            ageField.setText("");
            marksField.setText("");
            idField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Invalid number input!");
        }
    }

    private void updateStudent() {
        try {
            if(idField.getText().isEmpty()) { JOptionPane.showMessageDialog(frame,"Select a student first!"); return; }
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double marks = Double.parseDouble(marksField.getText());
            service.updateStudent(id, name, age, marks);
            loadTable();
            JOptionPane.showMessageDialog(frame,"Student updated!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Invalid number input!");
        }
    }

    private void deleteStudent() {
        try {
            if(idField.getText().isEmpty()) { JOptionPane.showMessageDialog(frame,"Select a student first!"); return; }
            int id = Integer.parseInt(idField.getText());
            service.deleteStudent(id);
            loadTable();
            JOptionPane.showMessageDialog(frame,"Student deleted!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,"Error deleting student!");
        }
    }

    private void sortByName() {
        model.setRowCount(0);
        for (Student s : service.sortByName()) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getMarks()});
        }
    }

    private void sortByMarks() {
        model.setRowCount(0);
        for (Student s : service.sortByMarks()) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getMarks()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProjectUI::new);
    }
}
