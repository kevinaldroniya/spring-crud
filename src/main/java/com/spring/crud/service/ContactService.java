package com.spring.crud.service;

import com.spring.crud.dto.*;
import com.spring.crud.entity.User;
import org.springframework.data.domain.Page;

public interface ContactService {
    public ContactResponse create(User user, CreateContactRequest request);

    public ContactResponse getContact(User user, String contactId);

    public ContactResponse updateContact(User user, String contactId, UpdateContactRequest request);

    public Page<ContactResponse> getAllContact(User user, SearchContactRequest request);
}
