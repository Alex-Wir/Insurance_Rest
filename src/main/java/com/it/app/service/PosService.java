package com.it.app.service;

import com.it.app.model.Pos;

import java.util.List;

public interface PosService {

    List<Pos> findAll();

    Pos findById(Long id);

    Pos save(Pos pos);

    Pos update(Pos pos);

    void delete(Pos pos);

    void deleteById(Long id);
}
