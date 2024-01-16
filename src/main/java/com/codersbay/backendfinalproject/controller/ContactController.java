package com.codersbay.backendfinalproject.controller;


import com.codersbay.backendfinalproject.exception.ContactNotFoundException;
import com.codersbay.backendfinalproject.exception.UserNotFoundException;
import com.codersbay.backendfinalproject.model.Contact;
import com.codersbay.backendfinalproject.model.User;
import com.codersbay.backendfinalproject.repository.ContactRepository;
import com.codersbay.backendfinalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.codersbay.backendfinalproject.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:3000")
public class ContactController {

    // inject the UserRepository
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    // newContact-method for Creation of a new Contact linked to a specific UserId
    //@PostMapping("/contact/{id}")
    @PostMapping("/users/{userId}/contacts")
    Contact newContact(@RequestBody Contact newContact,@PathVariable Long userId) {

        Optional<User> userWithId = userRepository.findById(userId);

        Contact savedContact= contactRepository.save(newContact);

        userWithId.get().getContacts().add(savedContact);

        userRepository.save(userWithId.get());

        Contact updatedContact = contactRepository.findById(savedContact.getId()).get();
        return updatedContact;
    }


    // Retrieving contacts with a specific userId (wichtig für frontend)
    //@GetMapping("/contacts/{userId}")
    @GetMapping("/users/{userId}/contacts")
    List<Contact> getAllContactsByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Set<Contact> contactsSet = user.get().getContacts();
            return new ArrayList<>(contactsSet);
        } else {
            throw new UserNotFoundException();
        }
    }


   /* @GetMapping("/contacts/{id}")
    List<Contact> getAllContactsByUserId(@PathVariable Long id) {
        // ...
    }*/

    // Retrieving the contacts of all users for test purpose
    @GetMapping("/contacts")
    List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }








/*

    // Retrieving the Contact from a user with userId with a specific contactId
    //@GetMapping("/contact/{id}")
    @GetMapping("/users/{userId}/contacts/{contactId}")
    Contact getContactById(@PathVariable Long contactId, @PathVariable String userId) {
        return contactRepository.findById(contactId)
                .orElseThrow(()->new ContactNotFoundException(contactId));
    }

*/


    // Neue Methode, um den Kontakt eines Benutzers anhand von userId und contactId zu erhalten
    @GetMapping("/users/{userId}/contacts/{contactId}")
    public Contact getContactByUserIdAndContactId(@PathVariable Long userId, @PathVariable Long contactId) {
        return userService.getContactForUser(userId, contactId);
    }





    // updating a contact with a specific contactId
    @PutMapping("/users/{userId}/contacts/{contactId}")
    Contact updateContact(@RequestBody Contact newContact, @PathVariable Long contactId, @PathVariable String userId) {
        return contactRepository.findById(contactId)
                .map(contact -> {
                    contact.setUsername(newContact.getUsername());
                    contact.setName(newContact.getName());
                    contact.setEmail(newContact.getEmail());
                    return contactRepository.save(contact);
                }).orElseThrow(()-> new ContactNotFoundException(contactId));
    }
/*
    // deleting a contact with a specific contact id
    @DeleteMapping("/contact/{id}")
    String deleteContact(@PathVariable Long id) {
        if(!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);

        }
        contactRepository.deleteById(id);
        return "Contact with id " + id + " has been deleted success.";
    }
    */

    // Delete a contact with contactId from a user with userId
    //@DeleteMapping("/contact/{id}")
    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    public String deleteContact(@PathVariable Long contactId, @PathVariable String userId) {
        if (!contactRepository.existsById(contactId)) {
            throw new ContactNotFoundException(contactId);
        }

        // Kontakt abrufen
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException(contactId));

        // Entfernen des Kontakts aus allen Benutzerbeziehungen
        for (User user : contact.getUsers()) {
            user.getContacts().remove(contact);
            userRepository.save(user);
        }

        // Löschen des Kontakts
        contactRepository.deleteById(contactId);
        return "Contact with id " + contactId + " has been deleted successfully.";
    }





}
