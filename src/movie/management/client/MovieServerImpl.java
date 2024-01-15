package movie.management.client;

import movie.management.web.data.Movie;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MovieServerImpl implements MovieServer {
	// URL du service web
    private final String webServiceUrl;

    public MovieServerImpl(String webServiceUrl) {
        this.webServiceUrl = webServiceUrl;
    }

    @Override
    public Boolean delete(Integer id) {
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

    @Override
    public Movie get(Integer id) {
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

    @Override
    public Integer add(String title, String year) {
        System.out.print("Adding " + title + "... ");
        WebClient c = WebClient.create(webServiceUrl);
        Movie s = new Movie(title, year);
        c.type(MediaType.APPLICATION_XML);
        Response r = c.post(s);
        if (r.getStatus() == 400) {
            System.out.println("Oops!");
            return null;
        }
        String uri = r.getHeaderString("Content-Location");
        System.out.println("OK.");
        return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
    }
}
