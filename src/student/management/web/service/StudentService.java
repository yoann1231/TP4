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
if(STUDENT_DATA.get(s.getId()) != null) {
return null;
}
s.setId(id);
STUDENT_DATA.put(id, s);
return s;
}
public boolean deleteStudent1(int id) {
if(STUDENT_DATA.get(id) == null) {
return false;
}
STUDENT_DATA.remove(id);
return true;
}
public Student getStudent(int id) {
return STUDENT_DATA.get(id);
}
}