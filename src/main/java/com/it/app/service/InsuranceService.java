package com.it.app.service;

import com.it.app.model.Insurance;

import java.util.List;

public interface InsuranceService {

    List<Insurance> findAll();

    List<Insurance> findAllByUserId(Long id);

    List<Insurance> findAllByCarNumber(String number);

    List<Insurance> findAllByYear(Long year);

    List<Insurance> findAllByYearAndPoint(Long year, Long pointId);

    Insurance findById(Long id);

    Insurance save(Insurance insurance);

    Insurance update(Insurance insurance);

    void delete(Insurance insurance);

    void deleteById(Long id);

}
