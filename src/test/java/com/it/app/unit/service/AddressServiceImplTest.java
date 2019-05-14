package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Address;
import com.it.app.repository.AddressRepository;
import com.it.app.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void testFindAll() {
        final List<Address> addressList = Collections.singletonList(new Address());
        when(addressRepository.findAll()).thenReturn(addressList);
        assertEquals(addressService.findAll(), addressList);
    }

    @Test
    public void testFindById() {
        final Address address = new Address();
        when(addressRepository.findById(any(Long.class))).thenReturn(Optional.of(address));
        assertEquals(addressService.findById(1L), address);
    }

    @Test
    public void testSave() {
        final Address address = new Address();
        when(addressRepository.saveAndFlush(address)).thenReturn(address);
        assertEquals(addressService.save(address), address);
    }

    @Test
    public void testUpdate() {
        final Address address = new Address();
        address.setId(1L);
        when(addressRepository.saveAndFlush(address)).thenReturn(address);
        assertEquals(addressService.update(address), address);
    }

    @Test
    public void testDelete() {
        final Address address = new Address();
        address.setId(1L);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        doNothing().when(addressRepository).delete(address);
        assertDoesNotThrow(() -> addressService.delete(address));
    }

    @Test
    public void testDeleteById() {
        final Address address = new Address();
        address.setId(1L);
        doNothing().when(addressRepository).deleteById(any(Long.class));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        assertDoesNotThrow(() -> addressService.deleteById(1L));
    }

}
