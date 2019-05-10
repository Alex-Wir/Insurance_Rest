package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Point;
import com.it.app.repository.PointRepository;
import com.it.app.service.AddressService;
import com.it.app.service.PointService;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PointServiceImpl implements PointService {

    private final LocalizedMessageSource localizedMessageSource;
    private final PointRepository pointRepository;
    private final AddressService addressService;
    private final UserService userService;

    @Override
    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    @Override
    public List<Point> findAllByUserId(Long id) {
        return new ArrayList(userService.findById(id).getPoints());
    }

    @Override
    public Point findById(Long id) {
        return pointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.point.notExist", new Object[]{})));
    }

    @Override
    public Point save(Point point) {
        validate(point.getId() != null, localizedMessageSource.getMessage("error.point.notHaveId", new Object[]{}));
        return saveAndFlush(point);
    }

    @Override
    public Point update(Point point) {
        validate(point.getId() == null, localizedMessageSource.getMessage("error.point.haveId", new Object[]{}));
        return saveAndFlush(point);
    }

    @Override
    public void delete(Point point) {
        final Long id = point.getId();
        validate(id == null, localizedMessageSource.getMessage("error.point.haveId", new Object[]{}));
        findById(id);
        pointRepository.delete(point);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        pointRepository.deleteById(id);
    }

    private Point saveAndFlush(Point point) {
        validate(point.getAddress() == null || point.getAddress().getId() == null, localizedMessageSource.getMessage("error.point.address.isNull", new Object[]{}));
        point.setAddress(addressService.findById(point.getAddress().getId()));
        return pointRepository.saveAndFlush(point);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
