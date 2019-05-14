package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Car;
import com.it.app.model.Insurance;
import com.it.app.model.Shift;
import com.it.app.model.User;
import com.it.app.repository.InsuranceRepository;
import com.it.app.service.impl.CarServiceImpl;
import com.it.app.service.impl.InsuranceServiceImpl;
import com.it.app.service.impl.ShiftServiceImpl;
import com.it.app.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceServiceImplTest {

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private ShiftServiceImpl shiftService;

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testFindAll() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAll()).thenReturn(insuranceList);
        assertEquals(insuranceService.findAll(), insuranceList);
    }

    @Test
    public void testFindAllByUserId() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAllByUserId(any(Long.class))).thenReturn(insuranceList);
        assertEquals(insuranceService.findAllByUserId(1L), insuranceList);
    }

    @Test
    public void testFindAllByCarNumber() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAllByCarNumber(any(String.class))).thenReturn(insuranceList);
        assertEquals(insuranceService.findAllByCarNumber("1234"), insuranceList);
    }

    @Test
    public void testFindAllByYear() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAllByYear(any(LocalDate.class), any(LocalDate.class))).thenReturn(insuranceList);
        assertEquals(insuranceService.findAllByYear(1L), insuranceList);
    }

    @Test
    public void testFindAllByPeriodAndPos() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAllByPeriodAndPos(any(LocalDate.class), any(LocalDate.class), any(Long.class))).thenReturn(insuranceList);
        assertEquals(insuranceService.findAllByPeriodAndPos("1948-01-01", "1984-01-01", 1L), insuranceList);
    }

    @Test
    public void testFindAllByPeriodAndPoint() {
        final List<Insurance> insuranceList = Collections.singletonList(new Insurance());
        when(insuranceRepository.findAllByPeriodAndPoint(any(LocalDate.class), any(LocalDate.class), any(Long.class))).thenReturn(insuranceList);
        assertEquals(insuranceService.findAllByPeriodAndPoint("1948-01-01", "1984-01-01", 1L), insuranceList);
    }

    @Test
    public void testFindById() {
        final Insurance insurance = new Insurance();
        when(insuranceRepository.findById(any(Long.class))).thenReturn(Optional.of(insurance));
        assertEquals(insuranceService.findById(1L), insurance);
    }

    @Test
    public void testSave() {
        final Insurance insurance = new Insurance();
        Car car = new Car();
        car.setId(1L);
        Shift shift = new Shift();
        shift.setId(1L);
        User user = new User();
        user.setId(1L);
        insurance.setCar(car);
        insurance.setShift(shift);
        insurance.setUser(user);
        when(carService.findById(any(Long.class))).thenReturn(car);
        when(shiftService.findById(any(Long.class))).thenReturn(shift);
        when(userService.findById(any(Long.class))).thenReturn(user);
        when(insuranceRepository.saveAndFlush(insurance)).thenReturn(insurance);
        assertEquals(insuranceService.save(insurance), insurance);
    }

    @Test
    public void testUpdate() {
        final Insurance insurance = new Insurance();
        insurance.setId(1L);
        Car car = new Car();
        car.setId(1L);
        Shift shift = new Shift();
        shift.setId(1L);
        User user = new User();
        user.setId(1L);
        insurance.setCar(car);
        insurance.setShift(shift);
        insurance.setUser(user);
        when(carService.findById(any(Long.class))).thenReturn(car);
        when(shiftService.findById(any(Long.class))).thenReturn(shift);
        when(userService.findById(any(Long.class))).thenReturn(user);
        when(insuranceRepository.saveAndFlush(insurance)).thenReturn(insurance);
        assertEquals(insuranceService.update(insurance), insurance);
    }

    @Test
    public void testDelete() {
        final Insurance insurance = new Insurance();
        insurance.setId(1L);
        when(insuranceRepository.findById(1L)).thenReturn(Optional.of(insurance));
        doNothing().when(insuranceRepository).delete(insurance);
        assertDoesNotThrow(() -> insuranceService.delete(insurance));
    }

    @Test
    public void testDeleteById() {
        final Insurance insurance = new Insurance();
        insurance.setId(1L);
        doNothing().when(insuranceRepository).deleteById(any(Long.class));
        when(insuranceRepository.findById(1L)).thenReturn(Optional.of(insurance));
        assertDoesNotThrow(() -> insuranceService.deleteById(1L));
    }
}
