package kg.ice.controller;

import kg.ice.model.Contact;
import kg.ice.service.ContactService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactControllerTest {

    private final List<Contact> contacts = new ArrayList<>();

    @Before
    public void setUp() {
        Contact contact = Contact.builder()
                .id(10L)
                .name("Michael")
                .birthDay(DateTime.now())
                .build();
        contacts.add(contact);
    }

    @Test
    public void testList() {
        ContactService contactService = mock(ContactService.class);
        when(contactService.getContacts()).thenReturn(contacts);
        ContactController contactController = new ContactController(contactService);
        List<Contact> contacts = contactController.contactList();
        assert contacts.size() == 1;
    }

    @Test
    public void create() {
        final Contact newContact = Contact.builder().id(12L).name("LeBron").birthDay(DateTime.now()).build();
        ContactService contactService = mock(ContactService.class);
        when(contactService.save(newContact)).thenAnswer(x -> {
            contacts.add(newContact);
            return newContact;
        });
    }
}
