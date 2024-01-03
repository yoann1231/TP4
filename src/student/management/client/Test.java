package student.management.client;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*;
import student.management.web.data.*;
public class Test {
 private static String webServiceUrl = "http://localhost:8080/student.management.web/api/students";
 public static void main(String[] args) {
 Student alice = new Student("Alice", 15.0f, add("Alice", 15.0f));
 Student bob = new Student("Bob", 13.0f, add("Bob", 13.0f));
 get(bob.getId());
 delete(bob.getId());
 }
 private static Integer add(String name, Float grade) {
 System.out.print("Adding " + name + "... ");
 WebClient c = WebClient.create(webServiceUrl);
 Student s = new Student(name, grade);
 Response r = c.post(s);
 if(r.getStatus() == 400) {
 System.out.println("Oops!");
 return null;
 }
 String uri = r.getHeaderString("Content-Location");
 System.out.println("OK.");
 return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
 }
 
 private static Boolean delete(Integer id) {
 System.out.print("Deleting " + id + "... ");
 WebClient c = WebClient.create(webServiceUrl).path(id);
 int status = c.delete().getStatus();
 if(status == 200) {
 System.out.println("OK.");
 return true;
 }
 System.out.println("Oops!");
 return false;
 }
 private static Student get(Integer id) {
 System.out.print("Getting " + id + "... ");
 WebClient c = WebClient.create(webServiceUrl).path(id);
 Student s = null;
 try {
 s = c.get(Student.class);
 System.out.println(s.toString());
 } catch(NotFoundException e) {
 System.out.println("Oops!");
 }
 return s;
 }
}