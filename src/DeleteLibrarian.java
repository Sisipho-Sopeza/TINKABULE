import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteLibrarian extends JFrame {
    static DeleteLibrarian frame;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new DeleteLibrarian();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public DeleteLibrarian() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblEnterId = new JLabel("Enter Id:");
        lblEnterId.setForeground(Color.white);

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            String sid = textField.getText();
            if (sid == null || sid.trim().equals("")) {
                JOptionPane.showMessageDialog(DeleteLibrarian.this, "Id can't be blank");
            } else {
                int id = Integer.parseInt(sid);
                boolean deleted = deleteLibrarian(id);
                if (deleted) {
                    JOptionPane.showMessageDialog(DeleteLibrarian.this, "Record deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(DeleteLibrarian.this, "Unable to delete given id!");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(e -> {
            AdminSuccess.main(new String[]{});
            frame.dispose();
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(39)
                                .addComponent(lblEnterId)
                                .addGap(57)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(107, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(175, Short.MAX_VALUE)
                                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                .addGap(140))
                        .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(322, Short.MAX_VALUE)
                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(19)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEnterId))
                                .addGap(33)
                                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(43)
                                .addComponent(btnNewButton)
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

    // Method to delete librarian record from CSV file
    private boolean deleteLibrarian(int id) {
        String csvFile = "librarians.csv"; // Replace with your CSV file path
        String tempFile = "temp.csv"; // Temporary file to write updated data

        File inputFile = new File(csvFile);
        File tempFileObj = new File(tempFile);

        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && Integer.parseInt(data[0]) == id) {
                    deleted = true;
                    continue; // Skip writing deleted record to temp file
                }
                writer.write(line + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the original file and rename the temp file to original
        if (deleted) {
            if (inputFile.delete()) {
                tempFileObj.renameTo(inputFile);
            } else {
                deleted = false;
            }
        }

        return deleted;
    }
}
