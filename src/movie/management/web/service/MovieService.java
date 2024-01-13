package movie.management.web.service;

import java.util.*;
import movie.management.web.data.Movie;

public class MovieService {
    private static Map<Integer, Movie> MOVIE_DATA = new HashMap<Integer, Movie>();

    private int getNewId() {
        int newId = 0;
        for (int id : MOVIE_DATA.keySet()) {
            if (newId < id) {
                newId = id;
            }
        }
        return ++newId;
    }

    public Movie addMovie(Movie movie) {
        int id = getNewId();
        if (MOVIE_DATA.get(movie.getId()) != null) {
            return null;
        }
        movie.setId(id);
        MOVIE_DATA.put(id, movie);
        return movie;
    }

    public boolean deleteMovie(int id) {
        if (MOVIE_DATA.get(id) == null) {
            return false;
        }
        MOVIE_DATA.remove(id);
        return true;
    }

    public boolean updateMovie(int id, Movie movie){
        if (MOVIE_DATA.get(id) == null) {
            return false;
        }
        MOVIE_DATA.put(id, movie);
        return true;
    }

    public Movie getMovie(int id) {
        return MOVIE_DATA.get(id);
    }

    public Collection<Movie> getAllMovies(){
        return MOVIE_DATA.values();
    }
}