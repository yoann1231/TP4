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

    /**
     * Ajoute un nouveau film.
     *
     * @param s Film à ajouter (au format XML).
     * @return Réponse HTTP avec le statut et les détails du film ajouté.
     */
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

    /**
     * Met à jour les informations d'un film existant.
     *
     * @param id            Identifiant du film à mettre à jour.
     * @param updatedMovie  Nouvelles informations du film (au format XML).
     * @return Réponse HTTP indiquant le succès ou l'échec de la mise à jour.
     */
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

    /**
     * Supprime un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à supprimer.
     * @return Réponse HTTP indiquant le succès ou l'échec de la suppression.
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteMovie(@PathParam("id") int id) {
        if (!service.deleteMovie(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    /**
     * Récupère les informations d'un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à récupérer.
     * @return Réponse HTTP avec les détails du film ou une erreur si non trouvé.
     */
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

    /**
     * Récupère la liste de tous les films.
     *
     * @return Réponse HTTP avec la liste des films ou une erreur si la liste est vide.
     */
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
