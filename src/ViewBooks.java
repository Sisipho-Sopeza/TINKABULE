import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewBooks extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private String[][] data;
    private String[] columnNames = {"Book ID", "Title", "Author", "Price", "Quantity", "Category"};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewBooks frame = new ViewBooks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewBooks() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        data = readDataFromCSV();
        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        contentPane.add(sp, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton btnAddBook = new JButton("Add Book");
        panel.add(btnAddBook);

        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
    }

    private String[][] readDataFromCSV() {
        List<String[]> books = CSVUtils.readAllBooks();
        String[][] dataArray = new String[books.size()][];
        for (int i = 0; i < books.size(); i++) {
            dataArray[i] = books.get(i);
        }
        return dataArray;
    }

    private void addBook() {
        JTextField bookIDField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField categoryField = new JTextField();

        Object[] message = {
            "Book ID:", bookIDField,
            "Title:", titleField,
            "Author:", authorField,
            "Price:", priceField,
            "Quantity:", quantityField,
            "Category:", categoryField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String[] newBook = {
                bookIDField.getText(),
                titleField.getText(),
                authorField.getText(),
                priceField.getText(),
                quantityField.getText(),
                categoryField.getText()
            };

            CSVUtils.writeBook(newBook);

            // Refresh the table with new data
            data = readDataFromCSV();
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        }
    }
}
