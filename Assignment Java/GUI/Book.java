import java.io.Serializable;

// Book class - stores information about a book
public class Book implements Serializable {
    int id;
    String title;
    String author;
    String genre;
    int year;
    boolean isAvailable;

    // Constructor
    Book(int id, String title, String author, String genre, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.isAvailable = true;
    }

    // Convert to file format
    String toFileLine() {
        return id + "|" + title + "|" + author + "|" + genre + "|" + year + "|" + isAvailable;
    }

    // Create book from file line
    static Book fromFileLine(String line) {
        String[] p = line.split("\\|");
        if (p.length != 6) return null;
        try {
            Book b = new Book(Integer.parseInt(p[0]), p[1], p[2], p[3], Integer.parseInt(p[4]));
            b.isAvailable = Boolean.parseBoolean(p[5]);
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    // Display book as table row
    void display() {
        System.out.printf("  %-4d %-28s %-18s %-12s %-6d %-10s%n",
            id, title, author, genre, year, isAvailable ? "Available" : "Borrowed");
    }
}
