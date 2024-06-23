import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IssueBookForm extends JFrame {
    static IssueBookForm frame;
    private final JPanel contentPane;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;

    public static void main(String[] args) {
        // Initialize CSV file with hardcoded student data
        initializeStudentData();

        EventQueue.invokeLater(() -> {
            try {
                frame = new IssueBookForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initializeStudentData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Doe", "john.doe@example.com", "1234567890"));
        students.add(new Student(2, "Jane Smith", "jane.smith@example.com", "2345678901"));
        students.add(new Student(3, "Alice Johnson", "alice.johnson@example.com", "3456789012"));
        students.add(new Student(4, "Bob Brown", "bob.brown@example.com", "4567890123"));
        students.add(new Student(5, "Charlie Davis", "charlie.davis@example.com", "5678901234"));
        students.add(new Student(6, "Diana Evans", "diana.evans@example.com", "6789012345"));
        students.add(new Student(7, "Ethan Foster", "ethan.foster@example.com", "7890123456"));

        for (Student student : students) {
            CSVUtils.write(student); // Assuming CSVUtils.write(Student student) is properly implemented
        }
    }

    public IssueBookForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 438, 414);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Issue Book ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setForeground(Color.white);

        JLabel lblBookName = new JLabel("Book Callno:");
        lblBookName.setForeground(Color.white);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        JLabel lblStudentId = new JLabel("Student Id:");
        lblStudentId.setForeground(Color.white);

        JLabel lblStudentName = new JLabel("Student Name:");
        lblStudentName.setForeground(Color.white);

        JLabel lblStudentContact = new JLabel("Student Contact:");
        lblStudentContact.setForeground(Color.white);

        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookcallno = textField_1.getText();
                int studentid = Integer.parseInt(textField_2.getText());
                String studentname = textField_3.getText();
                String studentcontact = textField_4.getText();

                if (IssueBookDao.checkBook(bookcallno)) {
                    int i = IssueBookDao.save(bookcallno, studentid, studentname, studentcontact);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(IssueBookForm.this, "Book issued successfully!");
                        // Optionally clear text fields after successful issue
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                    } else {
                        JOptionPane.showMessageDialog(IssueBookForm.this, "Sorry, unable to issue!");
                    }
                } else {
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Sorry, Callno doesn't exist!");
                }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Optional action for "Back" button
                JOptionPane.showMessageDialog(IssueBookForm.this, "Back button pressed.");
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Note: Please check Student ID Carefully before issuing book!");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setForeground(Color.RED);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(10, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblBookName)
                                                        .addComponent(lblStudentId)
                                                        .addComponent(lblStudentName, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblStudentContact, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addGap(10)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                                                .addGap(48))
                                        .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addGap(20)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblNewLabel_1)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(47)
                                                                .addComponent(btnBack)))
                                                .addGap(100))))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(146)
                                .addComponent(lblNewLabel)
                                .addContainerGap(235, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(37)
                                .addComponent(lblNewLabel)
                                .addGap(43)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBookName)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(28)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStudentId)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(28)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStudentName)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStudentContact)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBack))
                                .addGap(18)
                                .addComponent(lblNewLabel_1)
                                .addGap(25))
        );
        contentPane.setLayout(gl_contentPane);
    }
}

class IssueBookDao {
    private static final String CSV_FILE = "issued_books.csv";

    // Method to check if a book exists in the CSV file
    public static boolean checkBook(String bookcallno) {
        List<String[]> data = readDataFromCSV();
        for (String[] row : data) {
            if (row[0].equals(bookcallno)) {
                return true;
            }
        }
        return false;
    }

    // Method to save issued book details to the CSV file
    public static int save(String bookcallno, int studentid, String studentname, String studentcontact) {
        try {
            FileWriter csvWriter = new FileWriter(CSV_FILE, true);
            csvWriter.append(String.join(",", bookcallno, String.valueOf(studentid), studentname, studentcontact));
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            return 1; // Successful save
        } catch (IOException e) {
            e.printStackTrace();
            return 0; // Failed save
        }
    }

    // Method to read all data from the CSV file
    private static List<String[]> readDataFromCSV() {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(CSV_FILE));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] rowData = row.split(",");
                data.add(rowData);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
