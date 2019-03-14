package by.it.dao;

import by.it.model.Shift;

import java.util.List;

public interface ShiftDAO extends GenericDAO<Shift, Long> {

    List<Shift> findAllWithInsurances(Integer firstResult, Integer maxResult);
}
