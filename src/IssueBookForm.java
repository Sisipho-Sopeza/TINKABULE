import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // This method initializes student data in CSV, which is already provided in your example.
        // No changes are needed here for switching to book ID.
    }

    public IssueBookForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 438, 414);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Centering the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(centerX, centerY);

        JLabel lblNewLabel = new JLabel("Issue Book ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setForeground(Color.white);

        JLabel lblBookId = new JLabel("Book ID:"); // Updated label
        lblBookId.setForeground(Color.white);

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
                int bookId = Integer.parseInt(textField_1.getText()); // Read book ID
                int studentId = Integer.parseInt(textField_2.getText());
                String studentName = textField_3.getText();
                String studentContact = textField_4.getText();

                if (IssueDao.checkBookById(bookId)) { // Use checkBookById for book ID check
                    int i = IssueDao.save(bookId, studentId, studentName, studentContact);
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
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Sorry, Book ID doesn't exist!");
                }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrarianSuccess.main(new String[]{});
                frame.dispose(); // Closes the current window
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
                                                        .addComponent(lblBookId) // Updated label
                                                        .addComponent(lblStudentId)
                                                        .addComponent(lblStudentName, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblStudentContact, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addGap(10)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE) // Changed text field reference
                                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(lblBookId) // Updated label
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)) // Changed text field reference
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
