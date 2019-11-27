package ua.com.epam;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.service.AuthorService;
import ua.com.epam.service.BookService;
import ua.com.epam.service.GenreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test(description = "Test of CRUD operations for Book entity")
public class CRUDBookTest extends BaseTest {
    private Book expBook = testData.books().getRandomOne();
    private Author expAuthor = testData.authors().getRandomOne();
    private Genre expGenre = testData.genres().getRandomOne();
    private List<Book> bookList = testData.books().getDefaultBooks();
    private BookService bookService = new BookService(client);
    private AuthorService authorService = new AuthorService(client);
    private GenreService genreService = new GenreService(client);

    @BeforeMethod
    public void sendBooks() {
        authorService.postAuthor(expAuthor);
        genreService.postGenre(expGenre);
        bookService.sendBooks(bookList, expAuthor.getAuthorId().toString(), expGenre.getGenreId().toString() );
    }

    @Test(description = "Get all Books of special Author in special Genre")
    public void getAllBooksOfSpecialAuthorInSpecialGenre(){
        log.info("Performing getAllBooksOfSpecialAuthorInSpecialGenre test");

        bookService.getBooksByAuthorAndGenre(expAuthor.getAuthorId().toString(), expGenre.getGenreId().toString());
        validator.getBookValidator(bookService.getResponse())
                .validateGetAllBooksOfSpecialAuthorInSpecialGenre(1);
    }

    @Test(description = "Post single Book obj")
    public void postBook() {
        log.info("Performing postBook test");
        clean.books();

        bookService.postBook(expBook, expAuthor.getAuthorId().toString(), expGenre.getGenreId().toString() );

        validator.getBookValidator(bookService.getResponse()).validatePostBookSuccess(expBook);
    }

    @Test(description = "Get single Book obj which doesn't exist")
    public void getSingleBookFailed(){
        log.info("Performing getSingleBookFailed test");

        bookService.getBook("66666");

        validator.getBookValidator(bookService.getResponse()).assertStatusCodeNotFound();

    }

    @Test(description = "Get single Book obj")
    public void getSingleBook(){
        log.info("Performing getSingleBook test");

        bookService.getBook(expBook.getBookId().toString());

        validator.getBookValidator(bookService.getResponse()).validateGetBookSuccess(expBook);

    }

    @Test( description = "Get book by bookName.")
    public void searchBookByName() {
        log.info("Performing getBookByName test");

        Map<String,String> paramMap = new HashMap<String, String>(){{
            put("q", "Thinking");
        }};

        bookService.getBooksWithSearch(paramMap);

        validator.getBookValidator(bookService.getResponse())
                .validateGetBookByFieldSuccess(expBook.getBookName());
    }

    @Test(description = "Update single Book obj")
    public void updateBook(){
        log.info("Performing updateBook test");
        expBook.setBookName("OOP");

        bookService.updateBook(expBook);

        validator.getBookValidator(bookService.getResponse()).validateUpdateBookSuccess(expBook);
    }

    @Test(description = "Delete single Book obj")
    public void deleteBook(){
        log.info("Performing deleteBook test");
        bookService.deleteBook(expBook);

        validator.getBookValidator(bookService.getResponse()).assertStatusCodeDeleteSuccess();
    }

    @AfterMethod
    public void cleanUp() {
       clean.books();
       authorService.deleteAuthor(expAuthor);
       genreService.deleteGenre(expGenre);
    }
}
