package by.it.dao.impl;

import by.it.dao.PosDAO;
import by.it.model.Pos;

public class PosDAOImpl extends GenericDAOImpl<Pos, Long> implements PosDAO {
    private static PosDAOImpl instance;

    private PosDAOImpl() {
        super(Pos.class);
    }

    synchronized public static PosDAOImpl getInstance() {
        if (instance == null) {
            instance = new PosDAOImpl();
        }
        return instance;
    }

}
