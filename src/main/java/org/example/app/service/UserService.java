package org.example.app.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.User;

import java.net.URI;
import java.util.*;

@Path("/api/v1.0/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserService {
    // В реальній програмі використовується БД.
    // Для навчальних цілей застосовано статичний список.
    private static final List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(1L, "Alice", "alice@mail.com"));
        users.add(new User(2L, "Bob", "bob@mail.com"));
        users.add(new User(3L, "Lucy", "lucy@mail.com"));
        users.add(new User(4L, "Tom", "tom@mail.com"));
    }

    @GET
    public List<User> getUsers() {
        return users;
    }

    @GET
    @Path("{id: [0-9]+}")
    public User getUser(@PathParam("id") Long id) {
        User user = new User(id, null, null);

        int index = Collections.binarySearch(users, user, Comparator.comparing(User::getId));

        if (index >= 0)
            return users.get(index);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        if (Objects.isNull(user.getId()))
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        int index = Collections.binarySearch(users, user, Comparator.comparing(User::getId));

        if (index < 0) {
            users.add(user);
            return Response
                    .status(Response.Status.CREATED)
                    .location(URI.create(String.format("/api/v1.0/users/%s", user.getId())))
                    .build();
        } else
            throw new WebApplicationException(Response.Status.CONFLICT);
    }


    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(@PathParam("id") Long id, User user) {
        user.setId(id);
        int index = Collections.binarySearch(users, user, Comparator.comparing(User::getId));

        if (index >= 0) {
            User updatedUser = users.get(index);
//            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            users.set(index, updatedUser);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = new User(id, null, null);
        int index = Collections.binarySearch(users, user, Comparator.comparing(User::getId));

        if (index >= 0) {
            users.remove(index);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
