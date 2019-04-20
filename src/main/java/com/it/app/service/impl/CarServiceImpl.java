package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Car;
import com.it.app.repository.CarRepository;
import com.it.app.service.CarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final LocalizedMessageSource localizedMessageSource;

    private final CarRepository carRepository;

    public CarServiceImpl(LocalizedMessageSource localizedMessageSource, CarRepository carRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.car.notExist", new Object[]{})));
    }

    @Override
    public Car save(Car car) {
        validate(car.getId() != null, localizedMessageSource.getMessage("error.car.notHaveId", new Object[]{}));
        return carRepository.saveAndFlush(car);
    }

    @Override
    public Car update(Car car) {
        validate(car.getId() == null, localizedMessageSource.getMessage("error.car.haveId", new Object[]{}));
        return carRepository.saveAndFlush(car);
    }

    @Override
    public void delete(Car car) {
        validate(car.getId() == null, localizedMessageSource.getMessage("error.car.haveId", new Object[]{}));
        carRepository.delete(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
