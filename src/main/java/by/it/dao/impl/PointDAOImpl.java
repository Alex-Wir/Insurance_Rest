package by.it.dao.impl;

import by.it.dao.PointDAO;
import by.it.model.Point;

public class PointDAOImpl extends GenericDAOImpl<Point, Long> implements PointDAO {
    private static PointDAOImpl instance;

    private PointDAOImpl() {
        super(Point.class);
    }

    synchronized public static PointDAOImpl getInstance() {
        if (instance == null) {
            instance = new PointDAOImpl();
        }
        return instance;
    }
}
