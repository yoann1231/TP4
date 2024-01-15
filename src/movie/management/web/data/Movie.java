package movie.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private Integer id;
    private String title;
    private String year;

    public Movie() {
    }

    /**
     * Constructeur d'un objet Movie avec titre, année et identifiant spécifiés.
     *
     * @param title Titre du film.
     * @param year  Année de sortie du film.
     * @param id    Identifiant unique du film.
     */
    public Movie(String title, String year, Integer id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    /**
     * Constructeur d'un objet Movie avec titre et année spécifiés (l'identifiant est initialisé à null).
     *
     * @param title Titre du film.
     * @param year  Année de sortie du film.
     */
    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
        this.id = null;
    }

    /**
     * Obtient le titre du film.
     *
     * @return Titre du film.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit le titre du film.
     *
     * @param title Nouveau titre du film.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtient l'année de sortie du film.
     *
     * @return Année de sortie du film.
     */
    public String getYear() {
        return year;
    }

    /**
     * Définit l'année de sortie du film.
     *
     * @param year Nouvelle année de sortie du film.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Obtient l'identifiant unique du film.
     *
     * @return Identifiant unique du film.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du film.
     *
     * @param id Nouvel identifiant unique du film.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
