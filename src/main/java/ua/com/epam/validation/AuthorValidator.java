package ua.com.epam.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AuthorValidator {
    private Response response;
    //to parse JSON String to needed model (with correct date parsing possibility)
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    AuthorValidator(Response response){
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

    public void assertEqualsAuthor(Author expAuthor){

        String body = response.getBody();

        Author actAuthor = gson.fromJson(body, Author.class);
        Assert.assertEquals(expAuthor, actAuthor);
    }

    public void validateGetAuthorSuccess(Author expAuthor){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsAuthor(expAuthor);
    }

    public void validateGetAuthorByFieldSuccess(String authorField, String authorFieldValue){
        assertStatusCodeGetOrUpdateSuccess();

        List<Author> authorsArr = getAuthorArrayFromResponseBody();
        SoftAssert softAssert = new SoftAssert();
        for(Author a:authorsArr) {
            if (authorField.equals("authorName.first")) {
                softAssert.assertEquals(a.getAuthorName().getFirst(), authorFieldValue);
            }
            else if(authorField.equals("authorName.second")){
                softAssert.assertEquals(a.getAuthorName().getSecond(), authorFieldValue);
            }
        }
        softAssert.assertAll();
    }

    public void validateGetAuthorsWithPaginationAndSortSuccess(String orderType, int size){
        assertStatusCodeGetOrUpdateSuccess();

        validateSortAuthorsById(orderType);
        assertEqualsAuthorArrayLength(size);
    }

    public void validatePostAuthorSuccess(Author expAuthor){
        assertStatusCodePostSuccess();
        assertEqualsAuthor(expAuthor);
    }

    public void validateUpdateAuthorSuccess(Author expAuthor){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsAuthor(expAuthor);
    }

    public void validateSortAuthorsById(String orderType){
        List<Author> authorsArr = getAuthorArrayFromResponseBody();
        List<Integer> idArr = new ArrayList<>();
        for(Author a: authorsArr){
            idArr.add(a.getAuthorId().intValue());
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

    public void assertEqualsAuthorArrayLength(int expLen){
        List<Author> authorsArr = getAuthorArrayFromResponseBody();
        int len = authorsArr.size();
        Assert.assertEquals(len, expLen);
    }

    public List<Author> getAuthorArrayFromResponseBody(){
        String body = response.getBody();
        List<Author>  authorList = new ArrayList<>();
        JSONArray authorsArr = new JSONArray(body);
        for (int i=0; i < authorsArr.length(); i++) {
            JSONObject authorJson = authorsArr.getJSONObject(i);
            authorList.add(gson.fromJson(authorJson.toString(), Author.class));
        }
        return authorList;
    }
}
