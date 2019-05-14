package com.it.app.service;

import com.it.app.model.Insurance;

import java.util.List;

/**
 * Service interface for Insurance entity
 */
public interface InsuranceService {

    List<Insurance> findAll();

    List<Insurance> findAllByUserId(Long id);

    List<Insurance> findAllByCarNumber(String number);

    List<Insurance> findAllByYear(Long year);

    List<Insurance> findAllByPeriodAndPos(String periodFrom, String periodTo, Long posId);

    List<Insurance> findAllByPeriodAndPoint(String periodFrom, String periodTo, Long pointId);

    Insurance findById(Long id);

    Insurance save(Insurance insurance);

    Insurance update(Insurance insurance);

    void delete(Insurance insurance);

    void deleteById(Long id);

}
