import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// GUI class for Library Management System
public class LibraryGUI extends JFrame implements ActionListener {

    Library library = new Library("data/library_data.txt");

    JTextField txtTitle = new JTextField();
    JTextField txtAuthor = new JTextField();
    JTextField txtGenre = new JTextField();
    JTextField txtYear = new JTextField();
    JTextField txtSearch = new JTextField();
    JLabel lblId = new JLabel("None");

    JButton btnAdd = new JButton("Add");
    JButton btnUpdate = new JButton("Update");
    JButton btnDelete = new JButton("Delete");
    JButton btnClear = new JButton("Clear");
    JButton btnSearch = new JButton("Search");
    JButton btnShowAll = new JButton("Show All");

    String[] columns = {"ID", "Title", "Author", "Genre", "Year", "Status"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JTable table = new JTable(tableModel);

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        if (library.books.size() == 0) {
            library.addBook("The Alchemist", "Paulo Coelho", "Fiction", 1988);
            library.addBook("Atomic Habits", "James Clear", "Self-Help", 2018);
            library.addBook("The Art of War", "Sun Tzu", "Philosophy", 1910);
            library.addBook("Sapiens", "Yuval Noah Harari", "History", 2011);
            library.addBook("Rich Dad Poor Dad", "Robert Kiyosaki", "Finance", 1997);
        }

        // TOP - Form
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        formPanel.add(new JLabel(" ID:"));      formPanel.add(lblId);
        formPanel.add(new JLabel(" Title:"));    formPanel.add(txtTitle);
        formPanel.add(new JLabel(" Author:"));   formPanel.add(txtAuthor);
        formPanel.add(new JLabel(" Genre:"));    formPanel.add(txtGenre);
        formPanel.add(new JLabel(" Year:"));     formPanel.add(txtYear);
        formPanel.add(new JLabel(" Search:"));   formPanel.add(txtSearch);
        add(formPanel, BorderLayout.NORTH);

        // MIDDLE - Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);      btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);   btnPanel.add(btnClear);
        btnPanel.add(btnSearch);   btnPanel.add(btnShowAll);
        add(btnPanel, BorderLayout.CENTER);

        // BOTTOM - Table
        table.setPreferredScrollableViewportSize(new Dimension(780, 280));
        add(new JScrollPane(table), BorderLayout.SOUTH);

        // Register listeners
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnShowAll.addActionListener(this);

        // Click table row to fill form
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                lblId.setText(tableModel.getValueAt(row, 0).toString());
                txtTitle.setText(tableModel.getValueAt(row, 1).toString());
                txtAuthor.setText(tableModel.getValueAt(row, 2).toString());
                txtGenre.setText(tableModel.getValueAt(row, 3).toString());
                txtYear.setText(tableModel.getValueAt(row, 4).toString());
            }
        });

        loadTable();
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            if (txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title and Author required!");
                return;
            }
            try {
                int year = Integer.parseInt(txtYear.getText().trim());
                Book b = library.addBook(txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), year);
                JOptionPane.showMessageDialog(this, "Book added! ID: " + b.id);
                clearForm(); loadTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid year!");
            }
        }
        else if (e.getSource() == btnUpdate) {
            if (lblId.getText().equals("None")) {
                JOptionPane.showMessageDialog(this, "Select a book first!");
                return;
            }
            try {
                int id = Integer.parseInt(lblId.getText());
                int year = Integer.parseInt(txtYear.getText().trim());
                library.updateBook(id, txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), year);
                JOptionPane.showMessageDialog(this, "Book updated!");
                clearForm(); loadTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid year!");
            }
        }
        else if (e.getSource() == btnDelete) {
            if (lblId.getText().equals("None")) {
                JOptionPane.showMessageDialog(this, "Select a book first!");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Delete this book?");
            if (choice == JOptionPane.YES_OPTION) {
                library.deleteBook(Integer.parseInt(lblId.getText()));
                clearForm(); loadTable();
            }
        }
        else if (e.getSource() == btnClear) {
            clearForm();
        }
        else if (e.getSource() == btnSearch) {
            ArrayList<Book> results = library.searchByTitle(txtSearch.getText().trim());
            tableModel.setRowCount(0);
            for (Book b : results) {
                tableModel.addRow(new Object[]{b.id, b.title, b.author, b.genre, b.year,
                    b.isAvailable ? "Available" : "Borrowed"});
            }
        }
        else if (e.getSource() == btnShowAll) {
            loadTable();
        }
    }

    void loadTable() {
        tableModel.setRowCount(0);
        for (Book b : library.books) {
            tableModel.addRow(new Object[]{b.id, b.title, b.author, b.genre, b.year,
                b.isAvailable ? "Available" : "Borrowed"});
        }
    }

    void clearForm() {
        lblId.setText("None");
        txtTitle.setText("");  txtAuthor.setText("");
        txtGenre.setText("");  txtYear.setText("");
        txtSearch.setText("");
    }

    public static void main(String[] args) {
        new LibraryGUI().setVisible(true);
    }
}
