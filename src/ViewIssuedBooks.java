import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewIssuedBooks extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewIssuedBooks frame = new ViewIssuedBooks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewIssuedBooks() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        String[][] data = readDataFromCSV();
        String[] columnNames = {"Book ID", "Student ID", "Student Name", "Student Contact"};

        if (data != null) {
            table = new JTable(data, columnNames);
            JScrollPane sp = new JScrollPane(table);
            contentPane.add(sp, BorderLayout.CENTER);
        } else {
            JLabel lblError = new JLabel("Error: issued_books.csv not found or empty.");
            lblError.setForeground(Color.RED);
            lblError.setHorizontalAlignment(SwingConstants.CENTER);
            contentPane.add(lblError, BorderLayout.CENTER);
        }
    }

    // Method to read data from the CSV file
    private String[][] readDataFromCSV() {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("issued_books.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] rowData = row.split(",");
                data.add(rowData);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file not found or other IO exceptions
            JOptionPane.showMessageDialog(this, "Error: issued_books.csv not found or could not be read.");
            return null; // Return null or handle as appropriate in your application
        }

        // Convert List<String[]> to String[][]
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        return dataArray;
    }
}
