package ua.com.epam.utils.data;

import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.genre.Genre;

import java.util.List;

public interface BookData {
    Book getRandomOne();

    List<Book> getDefaultBooks();

}
