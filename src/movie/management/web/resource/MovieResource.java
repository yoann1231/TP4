package movie.management.web.resource;

import java.net.*;
import java.util.Collection;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import movie.management.web.data.Movie;
import movie.management.web.service.MovieService;

@Path("/movies")
public class MovieResource {
    MovieService service = new MovieService();
    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addMovie(Movie s) {
        Movie movie = service.addMovie(s);
        if (movie == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + movie.getId();
        return Response.status(Response.Status.CREATED)
                .entity(movie)
                .contentLocation(uri.resolve(newUri))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateMovie(@PathParam("id") int id, Movie updatedMovie) {
        boolean movie = service.updateMovie(id, updatedMovie);
        if (!movie) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteMovie(@PathParam("id") int id) {
        if (!service.deleteMovie(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovie(@PathParam("id") int id) {
        Movie movie = service.getMovie(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(movie).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllMovies() {
        Collection<Movie> movies = service.getAllMovies();
        if (movies == null || movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(movies).build();
    }
}
