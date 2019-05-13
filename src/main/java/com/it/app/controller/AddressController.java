package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.AddressRequestDto;
import com.it.app.dto.response.AddressResponseDto;
import com.it.app.model.Address;
import com.it.app.service.AddressService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Address controller
 */
@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final Mapper mapper;
    private final LocalizedMessageSource localizedMessageSource;

    /**
     * Find all Addresses
     *
     * @return - ResponseEntity with List<AddressResponseDto> and HttpStatus
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AddressResponseDto>> getAll() {
        final List<Address> addresses = addressService.findAll();
        final List<AddressResponseDto> addressResponseDtoList = addresses.stream()
                .map((address) -> mapper.map(address, AddressResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(addressResponseDtoList, HttpStatus.OK);
    }

    /**
     * Find Address by id
     *
     * @param id - Address id
     * @return - ResponceEntity with AddressResponseDto and HttpStatus
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AddressResponseDto> getOne(@PathVariable Long id) {
        final AddressResponseDto addressDto = mapper.map(addressService.findById(id), AddressResponseDto.class);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    /**
     * Save transient Address
     *
     * @param addressRequestDto - transient Address
     * @return - ResponseEntity with AddressResponseDto and HttpStatus
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressResponseDto> save(@Valid @RequestBody AddressRequestDto addressRequestDto) {
        addressRequestDto.setId(null);
        final AddressResponseDto addressResponseDto = mapper.map(addressService
                .save(getAddress(addressRequestDto)), AddressResponseDto.class);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    /**
     * Change persistent Address by id
     *
     * @param addressRequestDto - request with changed Address
     * @param id                - Address id
     * @return - ResponseEntity with AddressResponseDto and HttpStatus
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AddressResponseDto> update(@Valid @RequestBody AddressRequestDto addressRequestDto,
                                                     @PathVariable Long id) {
        if (!Objects.equals(id, addressRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource
                    .getMessage("controller.address.unexpectedId", new Object[]{}));
        }
        final AddressResponseDto addressResponseDto = mapper
                .map(addressService.update(getAddress(addressRequestDto)), AddressResponseDto.class);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    /**
     * Delete Address by id
     *
     * @param id - Address id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if (addressService.findById(id) != null) {
            addressService.deleteById(id);
        }
    }

    private Address getAddress(AddressRequestDto addressRequestDto) {
        return mapper.map(addressRequestDto, Address.class);
    }

}
