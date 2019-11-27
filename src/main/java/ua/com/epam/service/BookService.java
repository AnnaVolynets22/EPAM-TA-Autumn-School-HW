package ua.com.epam.service;

import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.book.Book;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.utils.helpers.QueryBuilder;

import java.util.List;
import java.util.Map;

import static ua.com.epam.config.URI.*;
import static ua.com.epam.config.URI.DELETE_GENRE_SINGLE_OBJ;

public class BookService {
    private RestClient client;

    public BookService(RestClient client) {
        this.client = client;
    }

    public void postBook(Book book, String authorId, String genreId){
        String uri =String.format(POST_BOOK_SINGLE_OBJ, authorId, genreId);
        client.post(uri, book);
    }

    public void sendBooks(List<Book> bookList, String authorId, String genreId){
        String uri =String.format(POST_BOOK_SINGLE_OBJ, authorId, genreId);
        for(Book a : bookList) {
            client.post(uri, a);
        }
    }

    public void getBooksByAuthorAndGenre(String authorId, String genreId){

        client.get(String.format(GET_BOOKS_BY_AUTHOR_AND_GENRE, authorId, genreId));
    }

    public void getBook(String bookId){
        client.get(String.format(GET_BOOK_SINGLE_OBJ, bookId));
    }

    public void getBooks(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(GET_ALL_BOOKS_ARR + url);
    }

    public void getBooksWithSearch(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(SEARCH_FOR_EXISTED_BOOKS_ARR + url);
    }

    public void updateBook(Book book){
        client.put(PUT_BOOK_SINGLE_OBJ, book);
    }

    public void deleteBook(Book book){
        client.delete(String.format(DELETE_BOOK_SINGLE_OBJ, book.getBookId().toString()));
    }

    public Response getResponse(){
        return client.getResponse();
    }
}
