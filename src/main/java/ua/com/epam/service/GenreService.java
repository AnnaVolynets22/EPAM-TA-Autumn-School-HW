package ua.com.epam.service;

import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.genre.Genre;
import ua.com.epam.utils.helpers.QueryBuilder;

import java.util.List;
import java.util.Map;

import static ua.com.epam.config.URI.*;

public class GenreService {
    private RestClient client;

    public GenreService(RestClient client) {
        this.client = client;
    }

    public void postGenre(Genre genre){
        client.post(POST_GENRE_SINGLE_OBJ, genre);
    }

    public void sendGenres(List<Genre> genreList){
        for(Genre a : genreList) {
            client.post(POST_GENRE_SINGLE_OBJ, a);
        }
    }

    public void getGenre(String genreId){
        client.get(String.format(GET_GENRE_SINGLE_OBJ, genreId));
    }

    public void getGenres(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(GET_ALL_GENRES_ARR + url);
    }

    public void getGenresWithSearch(Map<String,String> paramMap){
        String url = QueryBuilder.build(paramMap);
        client.get(SEARCH_FOR_EXISTED_GENRES_ARR + url);
    }

    public void updateGenre(Genre genre){
        client.put(PUT_GENRE_SINGLE_OBJ, genre);
    }

    public void deleteGenre(Genre genre){
        client.delete(String.format(DELETE_GENRE_SINGLE_OBJ, genre.getGenreId().toString()));
    }

    public Response getResponse(){
        return client.getResponse();
    }

}
