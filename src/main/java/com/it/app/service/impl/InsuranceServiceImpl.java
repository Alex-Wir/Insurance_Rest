package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Insurance;
import com.it.app.repository.InsuranceRepository;
import com.it.app.service.CarService;
import com.it.app.service.InsuranceService;
import com.it.app.service.ShiftService;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final LocalizedMessageSource localizedMessageSource;
    private final InsuranceRepository insuranceRepository;
    private final CarService carService;
    private final ShiftService shiftService;
    private final UserService userService;

    @Override
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public Insurance findById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.insurance.notExist", new Object[]{})));
    }

    @Override
    public List<Insurance> findAllByUserId(Long id) {
        return insuranceRepository.findAllByUserId(id);
    }

    @Override
    public List<Insurance> findAllByCarNumber(String number) {
        return insuranceRepository.findAllByCarNumber(number);
    }

    @Override
    public List<Insurance> findAllByYear(Long year) {
        LocalDate yearBegin = LocalDate.of(year.intValue(), 01, 01);
        LocalDate yearEnd = LocalDate.of(year.intValue(), 12, 31);
        return insuranceRepository.findAllByYear(yearBegin, yearEnd);
    }

    @Override
    public List<Insurance> findAllByYearAndPoint(Long year, Long pointId) {
        LocalDate yearBegin = LocalDate.of(year.intValue(), 01, 01);
        LocalDate yearEnd = LocalDate.of(year.intValue(), 12, 31);
        return insuranceRepository.findAllByYearAndPoint(yearBegin, yearEnd, pointId);
    }

    @Override
    public List<Insurance> findAllByPeriodAndPos(String periodFrom, String periodTo, Long posId) {
        LocalDate dateFrom = LocalDate.parse(periodFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateTo = LocalDate.parse(periodTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return insuranceRepository.findAllByPeriodAndPos(dateFrom, dateTo, posId);
    }

    @Override
    public Insurance save(Insurance insurance) {
        validate(insurance.getId() != null, localizedMessageSource.getMessage("error.insurance.notHaveId", new Object[]{}));
        return saveAndFlush(insurance);
    }

    @Override
    public Insurance update(Insurance insurance) {
        validate(insurance.getId() == null, localizedMessageSource.getMessage("error.insurance.haveId", new Object[]{}));
        return saveAndFlush(insurance);
    }

    @Override
    public void delete(Insurance insurance) {
        final Long id = insurance.getId();
        validate(id == null, localizedMessageSource.getMessage("error.insurance.haveId", new Object[]{}));
        findById(id);
        insuranceRepository.delete(insurance);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        insuranceRepository.deleteById(id);
    }

    private Insurance saveAndFlush(Insurance insurance) {
        validate(insurance.getCar() == null || insurance.getCar().getId() == null, localizedMessageSource.getMessage("error.insurance.car.isNull", new Object[]{}));
        validate(insurance.getShift() == null || insurance.getShift().getId() == null, localizedMessageSource.getMessage("error.insurance.shift.isNull", new Object[]{}));
        validate(insurance.getUser() == null || insurance.getUser().getId() == null, localizedMessageSource.getMessage("error.insurance.user.isNull", new Object[]{}));
        insurance.setCar(carService.findById(insurance.getCar().getId()));
        insurance.setShift(shiftService.findById(insurance.getShift().getId()));
        insurance.setUser(userService.findById(insurance.getUser().getId()));
        return insuranceRepository.saveAndFlush(insurance);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
