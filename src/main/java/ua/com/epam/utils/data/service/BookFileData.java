package ua.com.epam.utils.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.exception.FileIsEmptyException;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.utils.data.BookData;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookFileData implements BookData {
    private static Logger log = Logger.getLogger(BookFileData.class);

    private String filePath = "src/test/resources/test-data/books";
    private Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    @Override
    public Book getRandomOne() {
        log.info("Try to find one random Book...\n");

        List<Book> books = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            books = lines.map(s -> g.fromJson(s, Book.class)).collect(Collectors.toList());
            if (books.isEmpty()) {
                log.error("File by path " + filePath + " is empty!");
                throw new FileIsEmptyException();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        Book b = books.get(new Random().nextInt(books.size()));
        log.info("Book with bookId = '" + b.getBookId() + "' was found!");

        return b;
    }

    @Override
    public List<Book> getDefaultBooks() {
        log.info("Try to find first 10 books...\n");

        List<Book> books = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            books = lines.map(s -> g.fromJson(s, Book.class)).collect(Collectors.toList());
            if (books.isEmpty()) {
                log.error("File by path " + filePath + " is empty!");
                throw new FileIsEmptyException();
            }

            if (books.size() < 10) {
                log.warn("There are only " + books.size() + " books found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(books.size() + " books found!");

        return books;
    }
}
