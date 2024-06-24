import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class IssueDao {

    // Method to read data from CSV and check if the given bookId exists
    public static boolean checkBookById(int bookId) {
        boolean exists = false;
        String line = "";
        String csvFile = "books.csv"; // Adjust file path as necessary

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

    // Method to save issued book information
    public static int save(int bookId, int studentId, String studentName, String studentContact) {
        String csvFile = "issued_books.csv"; // Adjust file path as necessary

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile, true))) {
            pw.println(bookId + "," + studentId + "," + studentName + "," + studentContact);
            return 1; // Return 1 for successful save
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Return -1 for save failure
        }
    }
}