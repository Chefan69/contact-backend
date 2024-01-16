package com.codersbay.backendfinalproject.controller;

import com.codersbay.backendfinalproject.exception.ContactNotFoundException;
import com.codersbay.backendfinalproject.model.Contact;
import com.codersbay.backendfinalproject.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

class ContactControllerTests {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllContacts() {
        // Arrange
        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact());
        contactList.add(new Contact());

        when(contactRepository.findAll()).thenReturn(contactList);

        // Act
        List<Contact> result = contactController.getAllContacts();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testGetContactById() {
        // Arrange
        Long contactId = 1L;
        Contact contact = new Contact();
        contact.setId(contactId);

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        // Act
        Contact result = contactController.getContactById(contactId);

        // Assert
        assertEquals(contactId, result.getId());
    }

    @Test
    void testGetContactById_ContactNotFound() {
        // Arrange
        Long contactId = 1L;

        when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ContactNotFoundException.class, () -> contactController.getContactById(contactId));
    }

}
