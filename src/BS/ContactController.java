package BS;

// ContactController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{name}")
    public Contact getContact(@PathVariable String name) {
        return contactService.getContactByName(name);
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PutMapping("/{name}")
    public void updateContact(@PathVariable String name, @RequestBody Contact updatedContact) {
        contactService.updateContact(name, updatedContact);
    }

    @DeleteMapping("/{name}")
    public void deleteContact(@PathVariable String name) {
        contactService.deleteContact(name);
    }
}

