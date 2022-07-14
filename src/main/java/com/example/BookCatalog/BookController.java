package com.example.BookCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/books/checkBookAvailability/{title}")
    public ResponseEntity<?> checkBookAvailability(@PathVariable String title) {
        Book b = bookRepository.findBookByTitle(title);
        if (b == null) {
            return new ResponseEntity<CustomErrorType>
                    (new CustomErrorType("No such book"),HttpStatus.NOT_FOUND);
        }
        Copy copy = null;
        for (Copy c : b.getCopiesAvailable()) {
            if (c.isAvailable()) {
                c.setAvailable(false);
                copy = c;
                break;
            }
        }
        if (copy != null) {
            bookRepository.save(b);
            return new ResponseEntity<Copy>(copy, HttpStatus.OK);
        } else {
            return new ResponseEntity<CustomErrorType>
                    (new CustomErrorType("No copy available"),HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/books/{scancode}")
    public ResponseEntity<?> getBookByScancode(@PathVariable String scancode) {
        // Is result going to be null?
        Book book = bookRepository.findBookByScancode(scancode);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>
                    (new CustomErrorType("Book with scancode not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    //
    @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBooksByIsbn(@PathVariable String isbn) {
        List<Book> books = bookRepository.findBooksByIsbn(isbn);
        if (books==null) {
            return new ResponseEntity<CustomErrorType>
                    (new CustomErrorType("No books with isbn found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Books>(new Books(books),HttpStatus.OK);
    }

    //
    @GetMapping("/books/authors/{authorName}")
    public ResponseEntity<?> getBooksByAuthorName(@PathVariable String authorName) {
        //bookRepository
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/books/{copiesAvailable}")
    public ResponseEntity<?> getBooksByCopiesAvailable(@PathVariable Integer copiesAvailable) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/books/{title}")
    public ResponseEntity<Book> getBooksByTitle(@PathVariable String title) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
    Add, remove, update, find
     */

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        // get book by title
        // if already present, we will add this book to the LIST of books which is copies of this same book
        try {
            bookRepository.save(book);
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Could not add book");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books/{scancode}")
    public ResponseEntity<?> removeBook(@PathVariable String scancode) {
        Book book = bookRepository.findBookByScancode(scancode);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>
                    (new CustomErrorType("Book not present"),HttpStatus.NOT_FOUND);
        }
        //bookRepository DELETE the book with this scancode
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/books/{scancode}")
    public ResponseEntity<?> updateBook() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
