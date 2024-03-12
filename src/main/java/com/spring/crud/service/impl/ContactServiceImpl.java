package com.spring.crud.service.impl;

import com.spring.crud.dto.*;
import com.spring.crud.entity.Contact;
import com.spring.crud.entity.User;
import com.spring.crud.repository.ContactRepository;
import com.spring.crud.service.ContactService;
import com.spring.crud.service.ValidationService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ValidationService validationService;


    @Autowired
    private ContactRepository contactRepository;


    @Override
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    @Override
    public ContactResponse getContact(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );

        return toContactResponse(contact);
    }

    @Override
    public ContactResponse updateContact(User user, String contactId, UpdateContactRequest request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, contactId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());

        contactRepository.save(contact);


        return toContactResponse(contact);
    }

    @Override
    public Page<ContactResponse> getAllContact(User user, SearchContactRequest request) {
        Specification<Contact> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), user));

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
//
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<Contact> contactPage = contactRepository.findAll(specification, pageable);

        List<ContactResponse> list = contactPage.getContent().stream().map(this::toContactResponse).toList();


        return new PageImpl<>(list, pageable, contactPage.getTotalElements());
    }

    private ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
