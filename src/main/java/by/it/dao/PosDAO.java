package by.it.dao;

import by.it.model.Pos;

public interface PosDAO extends GenericDAO<Pos, Long> {

    void updateNumber(Long id, String newNumber);
}
