package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Address;
import com.it.app.repository.AddressRepository;
import com.it.app.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final LocalizedMessageSource localizedMessageSource;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.address.notExist", new Object[]{})));
    }

    @Override
    public Address save(Address address) {
        validate(address.getId() != null, localizedMessageSource.getMessage("error.address.notHaveId", new Object[]{}));
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address update(Address address) {
        validate(address.getId() == null, localizedMessageSource.getMessage("error.address.haveId", new Object[]{}));
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public void delete(Address address) {
        final Long id = address.getId();
        validate(id == null, localizedMessageSource.getMessage("error.address.haveId", new Object[]{}));
        findById(id);
        addressRepository.delete(address);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        addressRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
