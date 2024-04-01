package com.spring.crud.repository;

import com.spring.crud.entity.Address;
import com.spring.crud.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {
    Optional<Address> findFirstByContactAndId(Contact contact, String addressId);

    List<Address> findAllByContact(Contact contact);
}
