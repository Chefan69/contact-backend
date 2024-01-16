package com.codersbay.backendfinalproject.converter;

import com.codersbay.backendfinalproject.dto.ContactDTO;
import com.codersbay.backendfinalproject.dto.UserDTO;
import com.codersbay.backendfinalproject.model.Contact;
import com.codersbay.backendfinalproject.model.User;

public class DTOConverter {

    public static UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public static ContactDTO convertToContactDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setUsername(contact.getUsername());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        return dto;
    }



    public static User convertToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
