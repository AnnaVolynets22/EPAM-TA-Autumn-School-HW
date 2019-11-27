package ua.com.epam.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenreValidator {
    private Response response;
    //to parse JSON String to needed model (with correct date parsing possibility)
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    GenreValidator(Response response){
        this.response = response;
    }

    public void assertStatusCodePostSuccess(){
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    public void assertStatusCodeGetOrUpdateSuccess(){
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public void assertStatusCodeDeleteSuccess(){
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    public void assertStatusCodeConflict(){
        Assert.assertEquals(response.getStatusCode(), 409);
    }

    public void assertStatusCodeNotFound(){
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    public void assertEqualsGenre(Genre expGenre){

        String body = response.getBody();

        Genre actGenre = gson.fromJson(body, Genre.class);

        Assert.assertEquals(expGenre, actGenre);
    }

    public void validateGetGenreSuccess(Genre expGenre){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsGenre(expGenre);
    }

    public void validateGetGenreByFieldSuccess(String genreField, String genreFieldValue){
        assertStatusCodeGetOrUpdateSuccess();

        List<Genre> genresArr = getGenreArrayFromResponseBody();
        SoftAssert softAssert = new SoftAssert();
        for(Genre g:genresArr) {
            if (genreField.equals("genreName")) {
                softAssert.assertEquals(g.getGenreName(), genreFieldValue);
            }
            else if(genreField.equals("genreDescription")){
                softAssert.assertEquals(g.getGenreDescription(), genreFieldValue);
            }
            else if(genreField.equals("genreId")){
                softAssert.assertEquals(g.getGenreId(), genreFieldValue);
            }
        }
        softAssert.assertAll();
    }

    public void validateGetGenresWithPaginationAndSortSuccess(String orderType, int size){
        assertStatusCodeGetOrUpdateSuccess();

        validateSortgenresById(orderType);
        assertEqualsGenreArrayLength(size);
    }

    public void validatePostGenreSuccess(Genre expGenre){
        assertStatusCodePostSuccess();
        assertEqualsGenre(expGenre);
    }

    public void validateUpdateGenreSuccess(Genre expGenre){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsGenre(expGenre);
    }

    public void validateSortgenresById(String orderType){
        List<Genre> genresArr = getGenreArrayFromResponseBody();
        List<Integer> idArr = new ArrayList<>();
        for(Genre a: genresArr){
            idArr.add(a.getGenreId().intValue());
        }
        Assert.assertTrue(isSorted(idArr, orderType));
    }

    public static boolean isSorted(List<Integer> list, String orderType) {
        if (list.size() == 0 || list.size() == 1) {
            return true;
        }

        Iterator<Integer> iter = list.iterator();
        Integer current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (orderType.equals("asc") && previous.compareTo(current) > 0) {
                return false;
            }else if(orderType.equals("desc") && previous.compareTo(current) < 0){
                return false;
            }
            previous = current;
        }
        return true;
    }

    public void assertEqualsGenreArrayLength(int expLen){
        List<Genre> genresArr = getGenreArrayFromResponseBody();
        int len = genresArr.size();
        Assert.assertEquals(len, expLen);
    }

    public List<Genre> getGenreArrayFromResponseBody(){
        String body = response.getBody();
        List<Genre> genreList = new ArrayList<>();
        JSONArray genresArr = new JSONArray(body);
        for (int i=0; i < genresArr.length(); i++) {
            JSONObject genreJson = genresArr.getJSONObject(i);
            genreList.add(gson.fromJson(genreJson.toString(), Genre.class));
        }
        return genreList;
    }
}
