package com.it.app.service;

import com.it.app.model.Point;

import java.util.List;

public interface PointService {

    List<Point> findAll();

    Point findById(Long id);

    Point save(Point point);

    Point update(Point point);

    void delete(Point point);

    void deleteById(Long id);
}
