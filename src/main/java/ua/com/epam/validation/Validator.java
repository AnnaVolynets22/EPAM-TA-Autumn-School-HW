package ua.com.epam.validation;

import ua.com.epam.entity.Response;

public class Validator {
    public AuthorValidator getAuthorValidator(Response response){
        return new AuthorValidator(response);
    }

    public BookValidator getBookValidator(Response response){
        return new BookValidator(response);
    }

    public GenreValidator getGenreValidator(Response response){
        return new GenreValidator(response);
    }
}
