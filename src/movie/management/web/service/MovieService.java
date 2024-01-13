package movie.management.web.service;

import java.util.*;

import movie.management.web.data.Movie;

public class MovieService {

    private static Map<Integer, Movie> MOVIE_DATA = new HashMap<Integer, Movie>();

    private int getNewId() {
        int newId = 0;
        for (int id : MOVIE_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        return ++newId;
    }

    public Movie addMovie(Movie s) {
        int id = getNewId();
        if (MOVIE_DATA.get(s.getId()) != null) {
            return null;
        }
        s.setId(id);
        MOVIE_DATA.put(id, s);
        return s;
    }

    public boolean deleteMovie(int id) {
        if (MOVIE_DATA.get(id) == null) {
            return false;
        }
        MOVIE_DATA.remove(id);
        return true;
    }

    public Movie getMovie(int id) {
        return MOVIE_DATA.get(id);
    }
}