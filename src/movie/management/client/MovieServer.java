package movie.management.client;

import movie.management.web.data.Movie;

public interface MovieServer {

    Boolean delete(Integer id);

    Movie get(Integer id);

    Integer add(String title, String year);

}
