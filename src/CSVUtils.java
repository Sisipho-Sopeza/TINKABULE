import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    private static final String STUDENTS_CSV_FILE = "students.csv";
    private static final String LIBRARIANS_CSV_FILE = "librarians.csv";
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

    // Method to remove a specific line from a CSV file
    public static void removeLine(String fileName, String lineToRemove) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write a Student object to the CSV file
    public static void writeStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_CSV_FILE, true))) {
            writer.write(student.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all Student objects from the CSV file
    public static List<Student> readAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) { // Assuming CSV format: id,name,email,contact
                    students.add(new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to write a Librarian object to the CSV file
    public static void writeLibrarian(Librarian librarian) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIBRARIANS_CSV_FILE, true))) {
            writer.write(librarian.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all Librarian objects from the CSV file
    public static List<Librarian> readAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LIBRARIANS_CSV_FILE))) {
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

    // Method to write a single book line to the CSV file
    public static void writeBook(String[] bookData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_CSV_FILE, true))) {
            writer.write(String.join(",", bookData));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all books from the CSV file
    public static List<String[]> readAllBooks() {
        List<String[]> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) { // Assuming CSV format: bookID, title, author, price, quantity, category
                    books.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Example of how to use the removeLine method
    public static void main(String[] args) {
        // Example of how to remove a line from a CSV file based on a condition
        removeLine(STUDENTS_CSV_FILE, "2,John Doe,johndoe@email.com,1234567890");
    }

    public static void write(Student student) {
    }

    public static List<Librarian> readAll() {
        return List.of();
    }

    public static void write(Librarian librarian) {
    }
}
