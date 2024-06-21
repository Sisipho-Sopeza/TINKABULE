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

    /**
     * Create the frame.
     */
    public ViewIssuedBooks() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        String[][] data = readDataFromCSV();
        String[] columnNames = {"Book Call No", "Student ID", "Student Name", "Student Contact"};

        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);

        contentPane.add(sp, BorderLayout.CENTER);
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
        }

        // Convert List<String[]> to String[][]
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        return dataArray;
    }
}
