package com.it.app.service;

import com.it.app.model.Shift;

import java.util.List;

public interface ShiftService {

    List<Shift> findAll();

    Shift findById(Long id);

    Shift save(Shift shift);

    Shift update(Shift shift);

    void delete(Shift shift);

    void deleteById(Long id);
}
