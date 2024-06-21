import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    private static final String CSV_FILE = "librarians.csv";

    private static final String BOOKS_CSV_FILE = "books.csv";
    private static final String ISSUED_BOOKS_CSV_FILE = "issued_books.csv";

    // Method to write a single line to a CSV file
    public static void writeLine(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all lines from a CSV file
    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Method to remove a line from a CSV file based on a condition
    public static void removeLine(String fileName, String condition) {
        List<String> lines = readLines(fileName);
        List<String> newLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains(condition)) {
                newLines.add(line);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : newLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(Librarian librarian) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            writer.write(librarian.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Librarian> readAll() {
        List<Librarian> librarians = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) { // Assuming CSV format: name,password,email,address,city,contact
                    librarians.add(new Librarian(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return librarians;
    }
}

