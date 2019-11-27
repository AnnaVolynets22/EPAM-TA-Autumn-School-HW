package ua.com.epam;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.service.AuthorService;
import ua.com.epam.service.GenreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test(description = "Test of CRUD operations for Genre entity")
public class CRUDGenreTest extends BaseTest{
    private Genre expGenre = testData.genres().getRandomOne();
    private List<Genre> genreList = testData.genres().getDefaultGenres();
    private GenreService genreService = new GenreService(client);

    @BeforeMethod
    public void sendGenres() {
        genreService.sendGenres(genreList);
    }
    @Test(description = "Post single Genre obj")
    public void postGenre() {
        log.info("Performing postGenre test");
        clean.genres();

        genreService.postGenre(expGenre);

        validator.getGenreValidator(genreService.getResponse()).validatePostGenreSuccess(expGenre);
    }

    @Test(description = "Get single Genre obj which doesn't exist")
    public void getSingleGenreFailed(){
        log.info("Performing getSingleGenreFailed test");

        genreService.getGenre("6666");

        validator.getGenreValidator(genreService.getResponse()).assertStatusCodeNotFound();

    }

    @Test(description = "Get single Genre obj")
    public void getSingleGenre(){
        log.info("Performing getSingleGenre test");

        genreService.getGenre(expGenre.getGenreId().toString());

        validator.getGenreValidator(genreService.getResponse()).validateGetGenreSuccess(expGenre);

    }

    @Test( description = "Get genre by genreName.")
    public void getGenreByName() {
        log.info("Performing getGenreByName test");

        Map<String,String> paramMap = new HashMap<String, String>(){{
            put("query", expGenre.getGenreName());
        }};

        genreService.getGenresWithSearch(paramMap);

        validator.getGenreValidator(genreService.getResponse())
                .validateGetGenreByFieldSuccess("genreName", expGenre.getGenreName());
    }

    @Test(description = "Update single Genre obj")
    public void updateGenre(){
        log.info("Performing updateGenre test");
        expGenre.setGenreName("Fiction");

        genreService.updateGenre(expGenre);

        validator.getGenreValidator(genreService.getResponse()).validateUpdateGenreSuccess(expGenre);
    }

    @Test(description = "Delete single Genre obj")
    public void deleteGenre(){
        log.info("Performing deleteGenre test");
        genreService.deleteGenre(expGenre);

        validator.getGenreValidator(genreService.getResponse()).assertStatusCodeDeleteSuccess();
    }

    @AfterMethod
    public void cleanUp() {
        clean.genres();
    }
}
