package movie.management.client;

import movie.management.web.data.Movie;

public interface MovieServer {

    /**
     * Supprime un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à supprimer.
     * @return Vrai si la suppression est réussie, sinon faux.
     */
    Boolean delete(Integer id);

    /**
     * Récupère les informations d'un film en fonction de son identifiant.
     *
     * @param id Identifiant du film à récupérer.
     * @return Objet Movie représentant les informations du film.
     */
    Movie get(Integer id);

    /**
     * Ajoute un nouveau film avec le titre et l'année spécifiés.
     *
     * @param title Titre du nouveau film.
     * @param year  Année de sortie du nouveau film.
     * @return Identifiant du nouveau film ajouté.
     */
    Integer add(String title, String year);

}
