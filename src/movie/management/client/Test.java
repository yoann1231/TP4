package movie.management.client;

import javax.ws.rs.*;
import java.util.*;
import movie.management.web.data.Movie;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import movie.management.web.data.*;

public class Test {

    private static String webServiceUrl = "http://10.147.20.181:8080/movie.management.web/api/movies";
    private static String OMDbApiKey_STRING = "407c108c";

    public static void main(String[] args) {

        MovieServerImpl movieServer = new MovieServerImpl(webServiceUrl);


        Movie alice = new Movie("Alice", "2019",
                movieServer.add("Alice", "2019"));

        Movie bob = new Movie("Test", "2019",
                movieServer.add("Test", "2019"));

        Movie movie = movieServer.get(bob.getId());

        String titleMovie = movie.getTitle();
        int yearMovie = Integer.parseInt(movie.getYear());

        //Extra Api
        OMDbAPICaller omDbAPICaller = new OMDbAPICaller(OMDbApiKey_STRING);
        String movieAbstract = omDbAPICaller.searchMovie(titleMovie, yearMovie);
        System.out.println(movieAbstract);


        movieServer.delete(bob.getId());
    }

}