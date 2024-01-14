package movie.management.client;
import javax.ws.rs.*;
import java.util.*;
import movie.management.web.data.Movie;

import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import movie.management.web.data.*;
import javax.ws.rs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import movie.management.web.data.Movie;

import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import java.io.IOException;
import java.net.URI;

import movie.management.web.data.*;
public class Test {
    private static String webServiceUrl = "http://localhost:8080/movie.management.web/api/movies";

    public static void main(String[] args) {
        Movie alice = new Movie("Alice", 15.0f, add("Alice", 15.0f));
        Movie bob = new Movie("Bob", 13.0f, add("Bob", 13.0f));
        get(bob.getId());
        delete(bob.getId());        
        String apiKey = "407c108c";
        OMDbAPICaller omdbAPICaller = new OMDbAPICaller(apiKey);
        omdbAPICaller.searchMovie("man", 2020);
    }

    private static Integer add(String name, Float grade) {
        System.out.print("Adding " + name + "... ");
        WebClient c = WebClient.create(webServiceUrl);
        Movie s = new Movie(name, grade);
        Response r = c.post(s);
        if (r.getStatus() == 400) {
            System.out.println("Oops!");
            return null;
        }
        String uri = r.getHeaderString("Content-Location");
        System.out.println("OK.");
        return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
    }

    private static Boolean delete(Integer id) {
        System.out.print("Deleting " + id + "... ");
        WebClient c = WebClient.create(webServiceUrl).path(id);
        int status = c.delete().getStatus();
        if (status == 200) {
            System.out.println("OK.");
            return true;
        }
        System.out.println("Oops!");
        return false;
    }

    private static Movie get(Integer id) {
        System.out.print("Getting " + id + "... ");
        WebClient c = WebClient.create(webServiceUrl).path(id);
        Movie s = null;
        try {
            s = c.get(Movie.class);
            System.out.println(s.toString());
        } catch (NotFoundException e) {
            System.out.println("Oops!");
        }
        return s;
    }
}