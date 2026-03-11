package main;

import books.Book;
import service.LibraryService;
import exception.*;

public class Main {
    public static void main(String[] args) {
        LibraryService s = new LibraryService();
        s.addBook(new Book(1, "The Alchemist", "Coelho", 2));
        s.addBook(new Book(2, "Clean Code", "Martin", 1));

        s.viewBooks();

        try { s.issueBook(1); } 
        catch (Exception e) { System.out.println(e.getMessage()); }

        try { s.returnBook(1); } 
        catch (Exception e) { System.out.println(e.getMessage()); }

        s.viewBooks();
    }
}