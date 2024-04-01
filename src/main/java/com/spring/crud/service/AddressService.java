package com.spring.crud.service;

import com.spring.crud.dto.AddressResponse;
import com.spring.crud.dto.CreateAddressRequest;
import com.spring.crud.dto.UpdateAddressRequest;
import com.spring.crud.entity.User;

public interface AddressService {
    AddressResponse create(User user, CreateAddressRequest request);

    AddressResponse get(User user, String contactId, String addressId);

    AddressResponse update(User user, UpdateAddressRequest request);

    void remove(User user, String contactId, String addressId);
}
