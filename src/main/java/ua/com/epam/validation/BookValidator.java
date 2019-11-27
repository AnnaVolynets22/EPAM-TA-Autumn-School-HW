package ua.com.epam.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.book.Book;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookValidator {
    private Response response;
    //to parse JSON String to needed model (with correct date parsing possibility)
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    BookValidator(Response response){
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

    public void assertEqualsBook(Book expBook){

        String body = response.getBody();

        Book actBook = gson.fromJson(body, Book.class);
        Assert.assertEquals(expBook, actBook);
    }

    public void validateGetBookSuccess(Book expBook){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsBook(expBook);
    }

    public void validateGetBookByFieldSuccess(String bookName){
        assertStatusCodeGetOrUpdateSuccess();

        List<Book> booksArr = getBookArrayFromResponseBody();
        SoftAssert softAssert = new SoftAssert();
        for(Book a:booksArr) {
            softAssert.assertEquals(a.getBookName(), bookName);
        }
        softAssert.assertAll();
    }

    public void validateGetBooksWithPaginationAndSortSuccess(String orderType, int size){
        assertStatusCodeGetOrUpdateSuccess();

        validateSortBooksById(orderType);
        assertEqualsBookArrayLength(size);
    }

    public void validateGetAllBooksOfSpecialAuthorInSpecialGenre(int count){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsBookArrayLength(count);
    }


    public void validatePostBookSuccess(Book expBook){
        assertStatusCodePostSuccess();
        assertEqualsBook(expBook);
    }

    public void validateUpdateBookSuccess(Book expBook){
        assertStatusCodeGetOrUpdateSuccess();
        assertEqualsBook(expBook);
    }

    public void validateSortBooksById(String orderType){
        List<Book> booksArr = getBookArrayFromResponseBody();
        List<Integer> idArr = new ArrayList<>();
        for(Book a: booksArr){
            idArr.add(a.getBookId().intValue());
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

    public void assertEqualsBookArrayLength(int expLen){
        List<Book> booksArr = getBookArrayFromResponseBody();
        int len = booksArr.size();
        Assert.assertEquals(len, expLen);
    }

    public List<Book> getBookArrayFromResponseBody(){
        String body = response.getBody();
        List<Book>  bookList = new ArrayList<>();
        JSONArray booksArr = new JSONArray(body);
        for (int i=0; i < booksArr.length(); i++) {
            JSONObject bookJson = booksArr.getJSONObject(i);
            bookList.add(gson.fromJson(bookJson.toString(), Book.class));
        }
        return bookList;
    }
}
