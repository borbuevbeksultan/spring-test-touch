package kg.ice.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "birth_day", columnDefinition = "timestamp")
    private DateTime birthDay;

    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    public static class ContractBuilder {

        private Contact contact;

        public ContractBuilder() {
            contact = new Contact();
        }

        public ContractBuilder id(Long id) {
            contact.id = id;
            return this;
        }

        public ContractBuilder name(String name) {
            contact.name = name;
            return this;
        }

        public ContractBuilder birthDay(DateTime birthDay) {
            contact.birthDay = birthDay;
            return this;
        }

        public Contact build() {
            return contact;
        }

    }

}
