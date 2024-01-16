package com.codersbay.backendfinalproject.repository;

import com.codersbay.backendfinalproject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact,Long> {


}
