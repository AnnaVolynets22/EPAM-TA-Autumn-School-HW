package ua.com.epam.utils.data;

import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.genre.Genre;

import java.util.List;

public interface GenreData {
    Genre getRandomOne();

    List<Genre> getDefaultGenres();

}
