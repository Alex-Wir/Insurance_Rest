package by.it.dao;

import by.it.model.Insurance;

public interface InsuranceDAO extends GenericDAO<Insurance, Long> {

    void deleteByNumber(Long number);
}
