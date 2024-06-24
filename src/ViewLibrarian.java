import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ViewLibrarian extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private String[][] data;
    private String[] columnNames = {"Name", "Email", "Contact"};

    public ViewLibrarian(JFrame parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 179, 252));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Centering the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(centerX, centerY);

        data = readDataFromCSV();
        table = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(table);
        contentPane.add(sp, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton btnAddLibrarian = new JButton("Add Librarian");
        panel.add(btnAddLibrarian);

        btnAddLibrarian.addActionListener(e -> new LibrarianForm(ViewLibrarian.this));

        // Add back button
        JButton btnBack = new JButton("Back");
        panel.add(btnBack);

        btnBack.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });
    }

    public void refreshTable() {
        data = readDataFromCSV();
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private String[][] readDataFromCSV() {
        List<Librarian> librarians = CSVUtils.readAllLibrarians();
        String[][] dataArray = new String[librarians.size()][3];
        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            dataArray[i][0] = librarian.getName();
            dataArray[i][1] = librarian.getEmail();
            dataArray[i][2] = librarian.getContact();
        }
        return dataArray;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // This is just for testing purposes. In actual use,
                // ViewLibrarian should be created from AdminSuccess
                ViewLibrarian frame = new ViewLibrarian(new JFrame());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}