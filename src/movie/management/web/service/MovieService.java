package movie.management.web.service;

import java.util.*;
import movie.management.web.data.Movie;

public class MovieService {
    // Données des films stockées dans une map (clé : identifiant, valeur : objet Movie)
    private static Map<Integer, Movie> DONNEES_FILMS = new HashMap<Integer, Movie>();

    // Méthode pour obtenir un nouvel identifiant unique
    private int getNewId() {
        int newId = 0;
        for (int id : DONNEES_FILMS.keySet()) {
            if (newId < id) {
                newId = id;
            }
        }
        return ++newId;
    }

    /**
     * Ajoute un nouveau film.
     *
     * @param movie Film à ajouter.
     * @return Film ajouté avec un identifiant unique, ou null si l'identifiant existe déjà.
     */
    public Movie addMovie(Movie movie) {
        int id = getNewId();
        if (DONNEES_FILMS.get(movie.getId()) != null) {
            return null;
        }
        movie.setId(id);
        DONNEES_FILMS.put(id, movie);
        return movie;
    }

    /**
     * Supprime un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à supprimer.
     * @return True si la suppression est réussie, sinon False.
     */
    public boolean deleteMovie(int id) {
        if (DONNEES_FILMS.get(id) == null) {
            return false;
        }
        DONNEES_FILMS.remove(id);
        return true;
    }

    /**
     * Met à jour les informations d'un film existant.
     *
     * @param id    Identifiant du film à mettre à jour.
     * @param movie Nouvelles informations du film.
     * @return True si la mise à jour est réussie, sinon False.
     */
    public boolean updateMovie(int id, Movie movie){
        if (DONNEES_FILMS.get(id) == null) {
            return false;
        }
        DONNEES_FILMS.put(id, movie);
        return true;
    }

    /**
     * Récupère les informations d'un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à récupérer.
     * @return Film correspondant à l'identifiant, ou null si non trouvé.
     */
    public Movie getMovie(int id) {
        return DONNEES_FILMS.get(id);
    }

    /**
     * Récupère la liste de tous les films.
     *
     * @return Collection de tous les films.
     */
    public Collection<Movie> getAllMovies(){
        return DONNEES_FILMS.values();
    }
}
