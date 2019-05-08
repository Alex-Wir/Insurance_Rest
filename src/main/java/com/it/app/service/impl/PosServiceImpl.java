package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Pos;
import com.it.app.repository.PosRepository;
import com.it.app.service.PointService;
import com.it.app.service.PosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PosServiceImpl implements PosService {

    private final LocalizedMessageSource localizedMessageSource;
    private final PosRepository posRepository;
    private final PointService pointService;

    public PosServiceImpl(LocalizedMessageSource localizedMessageSource, PosRepository posRepository, PointService pointService) {
        this.localizedMessageSource = localizedMessageSource;
        this.posRepository = posRepository;
        this.pointService = pointService;
    }

    @Override
    public List<Pos> findAll() {
        return posRepository.findAll();
    }

    @Override
    public Pos findById(Long id) {
        return posRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.pos.notExist", new Object[]{})));

    }

    @Override
    public Pos save(Pos pos) {
        validate(pos.getId() != null, localizedMessageSource.getMessage("error.pos.notHaveId", new Object[]{}));
        validate(posRepository.existsByName(pos.getName()), localizedMessageSource.getMessage("error.pos.name.notUnique", new Object[]{}));
        return saveAndFlush(pos);
    }

    @Override
    public Pos update(Pos pos) {
        final Long posId = pos.getId();
        validate(posId == null, localizedMessageSource.getMessage("error.pos.haveId", new Object[]{}));
        final Pos duplicatePos = posRepository.findByName(pos.getName());
        final boolean isDuplicateExists = duplicatePos != null && !Objects.equals(duplicatePos.getId(), posId);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.pos.name.notUnique", new Object[]{}));
        return posRepository.saveAndFlush(pos);
    }

    @Override
    public void delete(Pos pos) {
        final Long id = pos.getId();
        validate(id == null, localizedMessageSource.getMessage("error.pos.haveId", new Object[]{}));
        findById(id);
        posRepository.delete(pos);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        posRepository.deleteById(id);
    }

    private Pos saveAndFlush(Pos pos) {
        validate(pos.getPoint() == null || pos.getPoint().getId() == null, localizedMessageSource.getMessage("error.pos.point.isNull", new Object[]{}));
        pos.setPoint(pointService.findById(pos.getPoint().getId()));
        return posRepository.saveAndFlush(pos);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
