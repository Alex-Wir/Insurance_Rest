package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Car;
import com.it.app.repository.CarRepository;
import com.it.app.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private CarRepository carRepository;

    @Test
    public void testFindAll() {
        final List<Car> carList = Collections.singletonList(new Car());
        when(carRepository.findAll()).thenReturn(carList);
        assertEquals(carService.findAll(), carList);
    }

    @Test
    public void testFindAllByNumber() {
        final List<Car> carList = Collections.singletonList(new Car());
        when(carRepository.findAllByNumber(any(String.class))).thenReturn(carList);
        assertEquals(carService.findAllByNumber("1234"), carList);
    }

    @Test
    public void testFindById() {
        final Car car = new Car();
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        assertEquals(carService.findById(1L), car);
    }

    @Test
    public void testSave() {
        final Car car = new Car();
        when(carRepository.saveAndFlush(car)).thenReturn(car);
        assertEquals(carService.save(car), car);
    }

    @Test
    public void testUpdate() {
        final Car car = new Car();
        car.setId(1L);
        when(carRepository.saveAndFlush(car)).thenReturn(car);
        assertEquals(carService.update(car), car);
    }

    @Test
    public void testDelete() {
        final Car car = new Car();
        car.setId(1L);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        doNothing().when(carRepository).delete(car);
        assertDoesNotThrow(() -> carService.delete(car));
    }

    @Test
    public void testDeleteById() {
        final Car car = new Car();
        car.setId(1L);
        doNothing().when(carRepository).deleteById(any(Long.class));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        assertDoesNotThrow(() -> carService.deleteById(1L));
    }

}
