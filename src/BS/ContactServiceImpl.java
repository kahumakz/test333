package BS;

// ContactServiceImpl.java
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public Contact getContactByName(String name) {
        // 实际应用中可能需要从数据库中查询
        return contacts.stream()
                .filter(contact -> contact.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addContact(Contact contact) {
        // 实际应用中可能需要将联系人保存到数据库
        contacts.add(contact);
    }

    @Override
    public void updateContact(String name, Contact updatedContact) {
        // 实际应用中可能需要更新数据库中的联系人信息
        Contact existingContact = getContactByName(name);
        if (existingContact != null) {
            existingContact.setAddress(updatedContact.getAddress());
            existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        }
    }

    @Override
    public void deleteContact(String name) {
        // 实际应用中可能需要从数据库中删除联系人
        contacts.removeIf(contact -> contact.getName().equals(name));
    }
}
