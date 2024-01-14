package movie.management.client;
import javax.ws.rs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import movie.management.web.data.Movie;
import okhttp3.OkHttpClient;

import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import java.io.IOException;
import java.net.URI;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;





import movie.management.web.data.*;
public class Test {
    private static String webServiceUrl = "http://localhost:8080/movie.management.web/api/movies";

    public static void main(String[] args) {
        Movie alice = new Movie("Alice", 15.0f, add("Alice", 15.0f));
        Movie bob = new Movie("Bob", 13.0f, add("Bob", 13.0f));
        get(bob.getId());
        delete(bob.getId());
        sendHttpRequest();
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
 private static void sendHttpRequest() {
	 try {
         URL url = new URL("https://imdb146.p.rapidapi.com/v1/find/?query=brad");
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();

         // Set request headers
         connection.setRequestMethod("GET");
         connection.setRequestProperty("X-RapidAPI-Key", "a5b2dd2924msh3e86cf94d9fcdf8p1311c7jsnc8c4d03f3eeb");
         connection.setRequestProperty("X-RapidAPI-Host", "imdb146.p.rapidapi.com");

         // Send GET request
         int responseCode = connection.getResponseCode();

         if (responseCode == HttpURLConnection.HTTP_OK) {
             // Read response
             BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             String inputLine;
             StringBuilder response = new StringBuilder();

             while ((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
             }

             in.close();

             // Print response
             System.out.println(response.toString());
         } else {
             System.out.println("GET request failed. Response Code: " + responseCode);
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }



 
}