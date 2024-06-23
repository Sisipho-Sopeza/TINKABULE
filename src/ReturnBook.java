import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReturnBook extends JFrame {
    static ReturnBook frame;
    private final JPanel contentPane;
    private final JTextField textField;
    private final JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new ReturnBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReturnBook() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 516, 413);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblReturnBook = new JLabel("Return Book");
        lblReturnBook.setForeground(Color.white);
        lblReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblBookCallno = new JLabel("Book Callno:");
        lblBookCallno.setForeground(Color.white);

        JLabel lblStudentId = new JLabel("Student Id:");
        lblStudentId.setForeground(Color.white);

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookCallno = textField.getText();
                int studentId;
                try {
                    studentId = Integer.parseInt(textField_1.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReturnBook.this, "Invalid student ID format. Please enter a valid integer.");
                    return;
                }
                boolean isReturned = returnBook(bookCallno, studentId);
                if (isReturned) {
                    JOptionPane.showMessageDialog(ReturnBook.this, "Book returned successfully!");
                    LibrarianSuccess.main(new String[]{});
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(ReturnBook.this, "Sorry, unable to return book!");
                }
            }
        });

        JLabel lblNewLabel = new JLabel("Note: Check the book properly!");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrarianSuccess.main(new String[]{});
                frame.dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(36)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblStudentId, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblBookCallno, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(56)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(139, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(210, Short.MAX_VALUE)
                                .addComponent(btnReturnBook, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addGap(176))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(205, Short.MAX_VALUE)
                                .addComponent(lblReturnBook)
                                .addGap(187))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(19)
                                .addComponent(lblNewLabel)
                                .addContainerGap(294, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(355, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addGap(46))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReturnBook)
                                .addGap(32)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBookCallno)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(34)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStudentId)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(29)
                                .addComponent(btnReturnBook, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addGap(23)
                                .addComponent(btnBack)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(lblNewLabel)
                                .addGap(72))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private boolean returnBook(String bookCallno, int studentId) {
        List<String> issuedBooks = CSVUtils.readLines("issued_books.csv");
        for (String line : issuedBooks) {
            String[] parts = line.split(",");
            String csvBookCallno = parts[0];
            int csvStudentId = Integer.parseInt(parts[1]);
            if (csvBookCallno.equals(bookCallno) && csvStudentId == studentId) {
                CSVUtils.removeLine("issued_books.csv", line); // Remove the line from CSV
                return true;
            }
        }
        return false;
    }
}
