package student.management.web.service;

import java.util.*;

import student.management.web.data.*;

public class StudentService {
    private static Map<Integer, Student> STUDENT_DATA = new HashMap<Integer, Student>();

    private int getNewId() {
        int newId = 0;
        for (int id : STUDENT_DATA.keySet()) {
            if (newId < id)
                newId = id;
        }
        return ++newId;
    }

    public Student addStudent(Student s) {
        int id = getNewId();
        if (STUDENT_DATA.get(s.getId()) != null) {
            return null;
        }
        s.setId(id);
        STUDENT_DATA.put(id, s);
        return s;
    }
}