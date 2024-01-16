package com.codersbay.backendfinalproject.service;

import com.codersbay.backendfinalproject.exception.ContactNotFoundException;
import com.codersbay.backendfinalproject.exception.UserNotFoundException;
import com.codersbay.backendfinalproject.model.Contact;
import com.codersbay.backendfinalproject.model.User;
import com.codersbay.backendfinalproject.repository.UserRepository;
import com.codersbay.backendfinalproject.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public UserService(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public Contact getContactForUser(Long userId, Long contactId) throws UserNotFoundException, ContactNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        return user.getContacts().stream()
                .filter(contact -> contact.getId().equals(contactId))
                .findFirst()
                .orElseThrow(() -> new ContactNotFoundException(contactId));
    }

}
