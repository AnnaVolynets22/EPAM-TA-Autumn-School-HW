package ua.com.epam;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.service.AuthorService;
import ua.com.epam.validation.AuthorValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Test(description = "Test of CRUD operations for Author entity")
public class CRUDAuthorTest extends BaseTest {
    private Author expAuthor = testData.authors().getRandomOne();
    private List<Author> authorList = testData.authors().getDefaultAuthors();
    private AuthorService authorService = new AuthorService(client);


    @BeforeMethod
    public void sendAuthors() {
        authorService.sendAuthors(authorList);
    }

    @Test(description = "Post single Author obj")
    public void postAuthor() {
        log.info("Performing postAuthor test");
        clean.authors();

        authorService.postAuthor(expAuthor);

        validator.getAuthorValidator(authorService.getResponse()).validatePostAuthorSuccess(expAuthor);

    }

    @Test(description = "Post single Author obj which already exist.")
    public void postAuthorFailed() {
        log.info("Performing postAuthorFailed test");

        authorService.postAuthor(expAuthor);

        validator.getAuthorValidator(authorService.getResponse()).assertStatusCodeConflict();

    }

    @Test(description = "Get single Author obj")
    public void getSingleAuthor(){
        log.info("Performing getSingleAuthor test");

        authorService.getAuthor(expAuthor.getAuthorId().toString());

        validator.getAuthorValidator(authorService.getResponse()).validateGetAuthorSuccess(expAuthor);

    }

    @Test(description = "Get single Author obj which doesn't exist")
    public void getSingleAuthorFailed(){
        log.info("Performing getSingleAuthorFailed test");

        authorService.getAuthor("6666666666");

        validator.getAuthorValidator(authorService.getResponse()).assertStatusCodeNotFound();

    }

    @Test( description = "Get author by first name.")
    public void getAuthorsByFirstname() {
        log.info("Performing getAuthorsByFirstname test");

        Map<String,String> paramMap = new HashMap<String, String>(){{
            put("query", expAuthor.getAuthorName().getFirst());
            put("orderType","asc");
            put("sortBy","authorId");
        }};

        authorService.getAuthorsWithSearch(paramMap);

        validator.getAuthorValidator(authorService.getResponse())
                .validateGetAuthorByFieldSuccess("authorName.first", expAuthor.getAuthorName().getFirst());
    }

    @Test( description = "Get author by surname.")
    public void getAuthorsBySurname() {
        log.info("Performing getAuthorsSurname test");

        Map<String,String> paramMap = new HashMap<String, String>(){{
            put("query", expAuthor.getAuthorName().getSecond());
        }};

        authorService.getAuthorsWithSearch(paramMap);

        validator.getAuthorValidator(authorService.getResponse())
                .validateGetAuthorByFieldSuccess("authorName.second", expAuthor.getAuthorName().getSecond());
    }

    @Test( description = "Get different authors.")
    public void getDifferentAuthors() {
        log.info("Performing getDifferentAuthors test");
        Map<String,String> paramMap = new HashMap<String, String>(){{
            put("orderType","asc");
            put("page","2");
            put("pagination","true");
            put("size","5");
            put("sortBy","authorId");
        }};

        authorService.getAuthors(paramMap);

        validator.getAuthorValidator(authorService.getResponse())
                .validateGetAuthorsWithPaginationAndSortSuccess("asc", 5);
}

    @Test(description = "Delete single Author obj")
    public void deleteAuthor(){
        log.info("Performing deleteAuthor test");
        authorService.deleteAuthor(expAuthor);

        validator.getAuthorValidator(authorService.getResponse()).assertStatusCodeDeleteSuccess();
    }

    @Test(description = "Delete twice the same Author obj")
    public void deleteTwiceSameAuthor(){
        log.info("Performing deleteTwiceSameAuthor test");
        authorService.deleteAuthor(expAuthor);
        validator.getAuthorValidator(authorService.getResponse()).assertStatusCodeDeleteSuccess();

        authorService.deleteAuthor(expAuthor);
        validator.getAuthorValidator(authorService.getResponse()).assertStatusCodeNotFound();
    }

    @Test(description = "Update single Author obj")
    public void updateAuthor(){
        log.info("Performing updateAuthor test");
        expAuthor.setNationality("Ukraine");

        authorService.updateAuthor(expAuthor);

        validator.getAuthorValidator(authorService.getResponse()).validateUpdateAuthorSuccess(expAuthor);

    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }
}
