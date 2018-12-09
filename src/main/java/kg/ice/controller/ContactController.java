package kg.ice.controller;

import kg.ice.model.Contact;
import kg.ice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    public List<Contact> contactList() {
        return contactService.getContacts();
    }

    public Contact create() {
        return contactService.save(null);
    }

}
