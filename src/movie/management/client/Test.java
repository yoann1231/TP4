package movie.management.client;

import javax.ws.rs.*;
import java.util.*;
import movie.management.web.data.Movie;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import movie.management.web.data.*;

public class Test {

    // URL du service web
    private static String webServiceUrl = "http://10.147.20.181:8080/movie.management.web/api/movies";
    
    // Clé API OMDb
    private static String OMDbApiKey_STRING = "407c108c";

    public static void main(String[] args) {

        // Créer une instance de MovieServerImpl
        MovieServerImpl movieServer = new MovieServerImpl(webServiceUrl);

        // Créer le film "Alice"
        Movie alice = new Movie("Alice", "2019", movieServer.add("Alice", "2019"));

        // Créer le film "Test"
        Movie bob = new Movie("Test", "2019", movieServer.add("Test", "2019"));

        // Obtenir les informations du film
        Movie movie = movieServer.get(bob.getId());

        // Extraire le titre et l'année du film
        String titleMovie = movie.getTitle();
        int yearMovie = Integer.parseInt(movie.getYear());

        // Appeler l'API OMDb supplémentaire
        OMDbAPICaller omDbAPICaller = new OMDbAPICaller(OMDbApiKey_STRING);
        String movieAbstract = omDbAPICaller.searchMovie(titleMovie, yearMovie);
        System.out.println(movieAbstract);

        // Supprimer le film
        movieServer.delete(bob.getId());
    }
}
