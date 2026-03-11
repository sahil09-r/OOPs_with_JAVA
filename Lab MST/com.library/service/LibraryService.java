package com.library.service;

import com.library.books.Book;
import com.library.exception.BookNotFoundException;
import com.library.exception.BookNotAvailableException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private static final String FILE_NAME = "books.txt";

    public void addBook(Book b) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getAvailableCopies());
            writer.newLine();
        }
    }

    public List<Book> viewBooks() throws IOException {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int bookId = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int availableCopies = Integer.parseInt(parts[3]);
                    books.add(new Book(bookId, title, author, availableCopies));
                }
            }
        }
        return books;
    }

    public void issueBook(int bookId) throws BookNotFoundException, BookNotAvailableException, IOException {
        List<Book> books = viewBooks();
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                found = true;
                if (book.getAvailableCopies() > 0) {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    updateBooksFile(books);
                } else {
                    throw new BookNotAvailableException("Book not available for issue.");
                }
                break;
            }
        }
        if (!found) {
            throw new BookNotFoundException("Book not found.");
        }
    }

    public void returnBook(int bookId) throws BookNotFoundException, IOException {
        List<Book> books = viewBooks();
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                found = true;
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                updateBooksFile(books);
                break;
            }
        }
        if (!found) {
            throw new BookNotFoundException("Book not found.");
        }
    }

    private void updateBooksFile(List<Book> books) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getAvailableCopies());
                writer.newLine();
            }
        }
    }
}