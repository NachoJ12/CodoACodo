package com.cac.minibank.mapper;

import com.cac.minibank.dto.AddressDTO;
import com.cac.minibank.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDto(Address address){
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCivicNumber(address.getCivicNumber());
        addressDTO.setApartment(address.getApartment());
        addressDTO.setFloor(address.getFloor());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setProvince(address.getProvince());

        return addressDTO;
    }

}
