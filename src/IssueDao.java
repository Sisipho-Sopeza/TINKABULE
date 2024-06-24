import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IssueDao {

    // Method to read data from CSV and check if the given bookId exists
    public static boolean checkBookById(int bookId) {
        boolean exists = false;
        String line = "";
        String csvFile = "issued_books.csv"; // Adjust file path as necessary

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim()); // Assuming bookId is the first column in CSV
                if (id == bookId) {
                    exists = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exists;
    }

    // Method to save issued book information - implementation depends on your specific logic
    public static int save(int bookId, int studentId, String studentName, String studentContact) {
        // Implement saving logic here, returning > 0 if successful, 0 or < 0 otherwise
        return 1; // Placeholder return value
    }
}
