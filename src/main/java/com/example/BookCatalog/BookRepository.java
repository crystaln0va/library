package com.example.BookCatalog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,Long> {

    @Query("{scancode : :#{#scancode}}")
    Book findBookByScancode(@Param("scancode") String scancode);

    // List of Books?
    @Query("{isbn: :#{#isbn}}")
    List<Book> findBooksByIsbn(@Param("isbn") String isbn);

    // Each book has a LIST of author names?
    @Query("{'authorNames.authorName': :#{#authorName}}")
    Book findBooksByAuthorName(@Param("authorName") String authorName);

    // Can integer be used here?
    @Query("{copiesAvailable: :#{#copiesAvailable}}")
    List<Book> findBooksByCopiesAvailable(@Param("copiesAvailable") Integer copiesAvailable);

    Book findBookByTitle(String title);

}
