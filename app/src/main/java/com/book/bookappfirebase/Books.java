package com.book.bookappfirebase;

/**
 * Created by kunalmnik95 on 10/10/17.
 */

public class Books
{
    private String book;
    private String author;

    public Books(){}

    public Books(String book, String author)
    {
        this.book = book;
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
