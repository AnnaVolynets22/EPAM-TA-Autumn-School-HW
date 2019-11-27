package ua.com.epam.service;

import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.utils.helpers.QueryBuilder;

import java.util.List;
import java.util.Map;

import static ua.com.epam.config.URI.*;

public class AuthorService {
    private RestClient client;

    public AuthorService(RestClient client) {
        this.client = client;
    }

    public void postAuthor(Author author){
        client.post(POST_AUTHOR_SINGLE_OBJ, author);
    }

    public void sendAuthors(List<Author> authorList){
        for(Author a : authorList) {
            client.post(POST_AUTHOR_SINGLE_OBJ, a);
        }
    }

    public void getAuthor(String authorId){
        client.get(String.format(GET_AUTHOR_SINGLE_OBJ, authorId));
    }

    public void getAuthors(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(GET_ALL_AUTHORS_ARR + url);
    }

    public void getAuthorsWithSearch(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(SEARCH_FOR_EXISTED_AUTHORS_ARR + url);
    }

    public void updateAuthor(Author author){
       client.put(PUT_AUTHOR_SINGLE_OBJ, author);
    }

    public void deleteAuthor(Author author){
        client.delete(String.format(DELETE_AUTHOR_SINGLE_OBJ, author.getAuthorId().toString()));
    }

    public Response getResponse(){
        return client.getResponse();
    }


}
