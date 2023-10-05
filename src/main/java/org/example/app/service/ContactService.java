package org.example.app.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.entity.Contact;

import java.net.URI;
import java.util.*;

@Path("/api/v1.0/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactService {
    // В реальній програмі використовується БД.
    // Для навчальних цілей застосовано статичний список.
    private static final List<Contact> CONTACTS;

    static {
        CONTACTS = new ArrayList<>();
        CONTACTS.add(new Contact(1L, "John", "555 555-5555"));
        CONTACTS.add(new Contact(2L, "Jack", "222 222-2222"));
        CONTACTS.add(new Contact(3L, "Lucy", "333 333-3333"));
        CONTACTS.add(new Contact(4L, "Emma", "777 777-7777"));
    }

    @GET
    public List<Contact> getContacts() {
        return CONTACTS;
    }

    @GET
    @Path("{id: [0-9]+}")
    public Contact getContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);

        int index = Collections.binarySearch(CONTACTS, contact, Comparator.comparing(Contact::getId));

        if (index >= 0)
            return CONTACTS.get(index);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createContact(Contact contact) {
        if (Objects.isNull(contact.getId()))
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        int index = Collections.binarySearch(CONTACTS, contact, Comparator.comparing(Contact::getId));

        if (index < 0) {
            CONTACTS.add(contact);
            return Response
                    .status(Response.Status.CREATED)
                    .location(URI.create(String.format("/api/v1.0/CONTACTS/%s", contact.getId())))
                    .build();
        } else
            throw new WebApplicationException(Response.Status.CONFLICT);
    }


    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        contact.setId(id);
        int index = Collections.binarySearch(CONTACTS, contact, Comparator.comparing(Contact::getId));

        if (index >= 0) {
            Contact updatedContact = CONTACTS.get(index);
//            updatedContact.setName(contact.getName());
            updatedContact.setPhone(contact.getPhone());
            CONTACTS.set(index, updatedContact);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteContact(@PathParam("id") Long id) {
        Contact contact = new Contact(id, null, null);
        int index = Collections.binarySearch(CONTACTS, contact, Comparator.comparing(Contact::getId));

        if (index >= 0) {
            CONTACTS.remove(index);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
