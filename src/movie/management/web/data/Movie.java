package movie.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private Integer id;
    private String title;
    private String year;

    public Movie() {
    }

    public Movie(String title, String year, Integer id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
        this.id = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
