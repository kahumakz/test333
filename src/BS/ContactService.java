package BS;


import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContactByName(String name);
    void addContact(Contact contact);
    void updateContact(String name, Contact updatedContact);
    void deleteContact(String name);
}

