import java.io.*;
import java.util.*;

// Library class - handles all book operations and file storage
public class Library {
    ArrayList<Book> books = new ArrayList<>();
    int nextId = 1;
    String fileName;

    Library(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    // --- CRUD Operations ---

    Book addBook(String title, String author, String genre, int year) {
        Book book = new Book(nextId++, title, author, genre, year);
        books.add(book);
        saveToFile();
        return book;
    }

    Book getBookById(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    boolean updateBook(int id, String title, String author, String genre, int year) {
        Book b = getBookById(id);
        if (b == null) return false;
        if (!title.isEmpty()) b.title = title;
        if (!author.isEmpty()) b.author = author;
        if (!genre.isEmpty()) b.genre = genre;
        if (year > 0) b.year = year;
        saveToFile();
        return true;
    }

    boolean deleteBook(int id) {
        Book b = getBookById(id);
        if (b == null) return false;
        books.remove(b);
        saveToFile();
        return true;
    }

    // --- Search ---

    ArrayList<Book> searchByTitle(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase()))
                results.add(b);
        }
        return results;
    }

    ArrayList<Book> searchByAuthor(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.author.toLowerCase().contains(keyword.toLowerCase()))
                results.add(b);
        }
        return results;
    }

    // --- Sorting ---

    ArrayList<Book> sortByTitle() {
        ArrayList<Book> sorted = new ArrayList<>(books);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).title.compareToIgnoreCase(sorted.get(j + 1).title) > 0) {
                    Book temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }

    ArrayList<Book> sortByYear() {
        ArrayList<Book> sorted = new ArrayList<>(books);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).year > sorted.get(j + 1).year) {
                    Book temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }

    // --- Borrow / Return ---

    boolean borrowBook(int id) {
        Book b = getBookById(id);
        if (b == null || !b.isAvailable) return false;
        b.isAvailable = false;
        saveToFile();
        return true;
    }

    boolean returnBook(int id) {
        Book b = getBookById(id);
        if (b == null || b.isAvailable) return false;
        b.isAvailable = true;
        saveToFile();
        return true;
    }

    // --- File Handling ---

    void saveToFile() {
        try {
            // Create data folder if it doesn't exist
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("NextID=" + nextId);
            writer.newLine();
            for (Book b : books) {
                writer.write(b.toFileLine());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("NextID=")) {
                    nextId = Integer.parseInt(line.substring(7));
                } else {
                    Book b = Book.fromFileLine(line);
                    if (b != null) books.add(b);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}
