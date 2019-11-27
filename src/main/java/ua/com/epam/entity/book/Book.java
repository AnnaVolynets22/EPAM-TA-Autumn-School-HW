package ua.com.epam.entity.book;

import ua.com.epam.entity.book.nested.Additional;

import java.util.Objects;

public class Book {
    private Long bookId;
    private String bookName;
    private String bookLanguage;
    private String bookDescription;
    private Long publicationYear;
    private Additional additional;

    Book(){}
    public Book(Long bookId, String bookName, String bookLanguage,
                String bookDescription, Long publicationYear, Additional additional) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookLanguage = bookLanguage;
        this.bookDescription = bookDescription;
        this.publicationYear = publicationYear;
        this.additional = additional;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Long getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Long publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Additional getAdditional() {
        return additional;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBookId().equals(book.getBookId()) &&
                getBookName().equals(book.getBookName()) &&
                getBookLanguage().equals(book.getBookLanguage()) &&
                Objects.equals(getBookDescription(), book.getBookDescription()) &&
                Objects.equals(getPublicationYear(), book.getPublicationYear()) &&
                Objects.equals(getAdditional(), book.getAdditional());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getBookName(), getBookLanguage(), getBookDescription(),
                getPublicationYear(), getAdditional());
    }
}
