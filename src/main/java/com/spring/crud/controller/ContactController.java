package com.spring.crud.controller;

import com.spring.crud.dto.*;
import com.spring.crud.entity.User;
import com.spring.crud.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request){
        ContactResponse contactResponse = contactService.create(user, request);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(
            path = "/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> getContact(
            User user,
            @PathVariable("contactId") String contactId){
        ContactResponse contactResponse = contactService.getContact(user, contactId);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PutMapping(
            path = "/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> editContact(
            User user,
            @PathVariable("contactId")String contactId,
            @RequestBody UpdateContactRequest request)
    {
        ContactResponse contactResponse = contactService.updateContact(user, contactId, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @DeleteMapping(
            path = "/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("contactId") String contactId){
        contactService.delete(user, contactId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> searchContact(
            User user,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ){
        SearchContactRequest request = SearchContactRequest.builder()
                .page(page)
                .size(size)
                .build();

        Page<ContactResponse> contactResponsePage = contactService.getAllContact(user, request);
        return WebResponse.<List<ContactResponse>>builder().data(contactResponsePage.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(contactResponsePage.getNumber())
                        .totalPage(contactResponsePage.getTotalPages())
                        .size(contactResponsePage.getSize()).build())
                .build();
    }
}
