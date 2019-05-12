package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Shift;
import com.it.app.repository.ShiftRepository;
import com.it.app.service.PosService;
import com.it.app.service.ShiftService;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final LocalizedMessageSource localizedMessageSource;
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final PosService posService;

    @Override
    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift findById(Long id) {
        return shiftRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.shift.notExist", new Object[]{})));
    }

    @Override
    public List<Shift> findAllByUserId(Long id) {
        return shiftRepository.findShiftsByUserId(id);
    }

    @Override
    public Shift save(Shift shift) {
        validate(shift.getId() != null, localizedMessageSource.getMessage("error.shift.notHaveId", new Object[]{}));
        return saveAndFlush(shift);
    }

    @Override
    public Shift update(Shift shift) {
        validate(shift.getId() == null, localizedMessageSource.getMessage("error.shift.haveId", new Object[]{}));
        return saveAndFlush(shift);
    }

    @Override
    public void delete(Shift shift) {
        final Long id = shift.getId();
        validate(id == null, localizedMessageSource.getMessage("error.shift.haveId", new Object[]{}));
        findById(id);
        shiftRepository.delete(shift);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        shiftRepository.deleteById(id);
    }

    private Shift saveAndFlush(Shift shift) {
        validate(shift.getUser() == null || shift.getUser().getId() == null, localizedMessageSource.getMessage("error.shift.user.isNull", new Object[]{}));
        validate(shift.getPos() == null || shift.getPos().getId() == null, localizedMessageSource.getMessage("error.shift.user.isNull", new Object[]{}));
        shift.setUser(userService.findById(shift.getUser().getId()));
        shift.setPos(posService.findById(shift.getPos().getId()));
        return shiftRepository.saveAndFlush(shift);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
