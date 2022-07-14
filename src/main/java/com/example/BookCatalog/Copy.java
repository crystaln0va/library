package com.example.BookCatalog;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Copy {
    @Id
    private Long id;
    private boolean available;
    @Indexed(unique = true)
    private String scancode;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }
}
