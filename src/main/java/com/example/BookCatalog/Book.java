package com.example.BookCatalog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Book {
    @Id
    private long id;
    private String isbn;
    private List<String> authorNames;
    List<Copy> copiesAvailable;
    private String title;

    public Book(String isbn, List<String> authorNames, String title) {
        this.isbn = isbn;
        this.authorNames = authorNames;
        this.copiesAvailable = new ArrayList<>();
        this.title = title;
    }

    public Book() {}

    public void addCopy(Copy copy) {
        copiesAvailable.add(copy);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public List<Copy> getCopiesAvailable() {
        return copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
