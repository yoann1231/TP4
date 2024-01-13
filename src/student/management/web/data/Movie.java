package student.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    private String name;
    private Float grade;
    private Integer id;

    public Student() {
    }

    public Student(String name, Float grade, Integer id) {
        this.name = name;
        this.grade = grade;
        this.id = id;
    }

    public Student(String name, Float grade) {
        this.name = name;
        this.grade = grade;
        this.id = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
