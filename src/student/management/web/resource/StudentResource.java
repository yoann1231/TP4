package student.management.web.resource;

import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import student.management.web.data.*;
import student.management.web.service.*;

@Path("/students")
public class StudentResource {
    StudentService service = new StudentService();
    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addStudent(Student s) {
        Student student = service.addStudent(s);
        if (student == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        URI uri = uriInfo.getRequestUri();
        String newUri = uri.getPath() + "/" + student.getId();
        return Response.status(Response.Status.CREATED)
                .entity(student)
                .contentLocation(uri.resolve(newUri))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteStudent(@PathParam("id") int id) {
        if (service.deleteStudent(id) == false) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStudent(@PathParam("id") int id) {
        Student student = service.getStudent(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(student).build();
    }

    @GET
    @Path("/{id1}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getStudent1(@PathParam("id1") int id) {
        Student student = service.getStudent(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(student).build();
    }
}
