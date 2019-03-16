package by.it.dao;

import by.it.model.Point;

import java.util.List;

public interface PointDAO extends GenericDAO<Point, Long> {

    List<Point> findAll(Integer firstResult, Integer maxResult);
}
