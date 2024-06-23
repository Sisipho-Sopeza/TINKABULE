import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewLibrarian extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private String[][] data;
    private String[] columnNames = {"Name", "Email", "Contact"};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewLibrarian frame = new ViewLibrarian();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewLibrarian() {
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

        JButton btnAddLibrarian = new JButton("Add Librarian");
        panel.add(btnAddLibrarian);

        btnAddLibrarian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LibrarianForm(ViewLibrarian.this);
            }
        });
    }

    public void refreshTable() {
        data = readDataFromCSV();
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private String[][] readDataFromCSV() {
        List<Librarian> librarians = CSVUtils.readAll();
        String[][] dataArray = new String[librarians.size()][3];
        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            dataArray[i][0] = librarian.getName();
            dataArray[i][1] = librarian.getEmail();
            dataArray[i][2] = librarian.getContact();
        }
        return dataArray;
    }
}
