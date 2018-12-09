package kg.ice.service;

import kg.ice.annotation.DataSets;
import kg.ice.config.ContextConfig;
import kg.ice.config.ServiceTestExecutionListener;
import kg.ice.model.Contact;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class ContactServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ContactService contactService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DataSets(setUpDataSet = "/data.xls")
    public void testGetContacts() {
        List<Contact> contacts = contactService.getContacts();
        assert contacts.size() == 2;
    }

    @Test
    public void testOnSave() {
        deleteFromTables("contacts");
        Contact contact = Contact.builder().name("Kobe").birthDay(DateTime.now()).build();
        contactService.save(contact);
        entityManager.flush();
        List<Contact> contacts = contactService.getContacts();
    }

}
