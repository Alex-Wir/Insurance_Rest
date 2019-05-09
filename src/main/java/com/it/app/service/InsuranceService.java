package com.it.app.service;

import com.it.app.model.Insurance;

import java.util.List;
import java.util.Set;

public interface InsuranceService {

    List<Insurance> findAll();

    Insurance findById(Long id);

    Insurance save(Insurance insurance);

    Insurance update(Insurance insurance);

    void delete(Insurance insurance);

    void deleteById(Long id);

    List<Insurance> findInsurancesByUserId(Long id);

}
