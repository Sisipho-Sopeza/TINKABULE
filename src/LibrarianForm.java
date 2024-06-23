import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianForm extends JFrame {
    private JTextField textFieldName;
    private JPasswordField passwordField;
    private JTextField textFieldEmail;
    private JTextField textFieldAddress;
    private JTextField textFieldCity;
    private JTextField textFieldContact;
    private ViewLibrarian parentFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LibrarianForm frame = new LibrarianForm(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LibrarianForm(ViewLibrarian parentFrame) {
        this.parentFrame = parentFrame;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAddLibrarian = new JLabel("Add Librarian");
        lblAddLibrarian.setForeground(Color.white);
        lblAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblAddLibrarian.setBounds(150, 26, 150, 30);
        contentPane.add(lblAddLibrarian);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(58, 87, 70, 20);
        lblName.setForeground(Color.white);
        contentPane.add(lblName);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(58, 118, 70, 20);
        lblPassword.setForeground(Color.white);
        contentPane.add(lblPassword);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(58, 149, 70, 20);
        lblEmail.setForeground(Color.white);
        contentPane.add(lblEmail);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(58, 180, 70, 20);
        lblAddress.setForeground(Color.white);
        contentPane.add(lblAddress);

        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(58, 211, 70, 20);
        lblCity.setForeground(Color.white);
        contentPane.add(lblCity);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setBounds(58, 242, 86, 20);
        lblContactNo.setForeground(Color.white);
        contentPane.add(lblContactNo);

        textFieldName = new JTextField();
        textFieldName.setBounds(162, 87, 180, 20);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(162, 118, 180, 20);
        contentPane.add(passwordField);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(162, 149, 180, 20);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(162, 180, 180, 20);
        contentPane.add(textFieldAddress);
        textFieldAddress.setColumns(10);

        textFieldCity = new JTextField();
        textFieldCity.setBounds(162, 211, 180, 20);
        contentPane.add(textFieldCity);
        textFieldCity.setColumns(10);

        textFieldContact = new JTextField();
        textFieldContact.setBounds(162, 242, 180, 20);
        contentPane.add(textFieldContact);
        textFieldContact.setColumns(10);

        JButton btnAddLibrarian = new JButton("Add Librarian");
        btnAddLibrarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = textFieldName.getText();
                String password = String.valueOf(passwordField.getPassword());
                String email = textFieldEmail.getText();
                String address = textFieldAddress.getText();
                String city = textFieldCity.getText();
                String contact = textFieldContact.getText();

                Librarian librarian = new Librarian(name, password, email, address, city, contact);
                CSVUtils.write(librarian);

                JOptionPane.showMessageDialog(LibrarianForm.this, "Librarian added successfully!");
                if (parentFrame != null) {
                    parentFrame.refreshTable();
                }
                dispose();
            }
        });
        btnAddLibrarian.setBounds(162, 273, 120, 23);
        contentPane.add(btnAddLibrarian);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parentFrame != null) {
                    parentFrame.setVisible(true);
                }
                dispose();
            }
        });
        btnBack.setBounds(292, 273, 89, 23);
        contentPane.add(btnBack);
    }
}
