import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Book implements Serializable {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String placeHold() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Library implements Serializable {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}

public class LibraryManagementSystem extends JFrame {
    private Library library;
    private JTextArea displayArea;
    private JTextField usernameField, passwordField, bookTitleField, bookAuthorField;

    public LibraryManagementSystem() {
        super("Library Management System");
        library = new Library();

        // Sample books and users
        library.addBook(new Book("Java Programming", "John Doe"));
        library.addBook(new Book("Data Structures", "Jane Smith"));
        library.addUser(new User("user1", "password1"));
        library.addUser(new User("user2", "password2"));

        // GUI Components
        JPanel panel = new JPanel(new BorderLayout());

        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginButton);
        panel.add(loginPanel, BorderLayout.NORTH);

        // Book Management Panel
        JPanel bookPanel = new JPanel(new GridLayout(3, 2));
        bookPanel.add(new JLabel("Title:"));
        bookTitleField = new JTextField();
        bookPanel.add(bookTitleField);
        bookPanel.add(new JLabel("Author:"));
        bookAuthorField = new JTextField();
        bookPanel.add(bookAuthorField);
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        bookPanel.add(addBookButton);
        panel.add(bookPanel, BorderLayout.CENTER);

        // Display Area
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        add(panel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        for (User user : library.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                displayBooks();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid username or password.");
    }

    private void addBook() {
        String title = bookTitleField.getText();
        String author = bookAuthorField.getText();
        library.addBook(new Book(title, author));
        displayBooks();
        JOptionPane.showMessageDialog(this, "Book added successfully.");
    }

    private void displayBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Books in Library:\n");
        for (Book book : library.getBooks()) {
            sb.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor());
            if (book.isAvailable()) {
                sb.append(" (Available)\n");
            } else {
                sb.append(" (Not Available)\n");
            }
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}
