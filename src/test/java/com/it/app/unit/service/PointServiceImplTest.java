package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Address;
import com.it.app.model.Point;
import com.it.app.model.User;
import com.it.app.repository.PointRepository;
import com.it.app.service.impl.AddressServiceImpl;
import com.it.app.service.impl.PointServiceImpl;
import com.it.app.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PointServiceImplTest {

    @InjectMocks
    private PointServiceImpl pointService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private PointRepository pointRepository;

    @Mock
    private AddressServiceImpl addressService;

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testFindAll() {
        final List<Point> pointList = Collections.singletonList(new Point());
        when(pointRepository.findAll()).thenReturn(pointList);
        assertEquals(pointService.findAll(), pointList);
    }

    @Test
    public void testFindAllByUserId() {
        final List<Point> pointList = Collections.singletonList(new Point());
        User user = new User();
        Set<Point> pointSet = new HashSet<>(pointList);
        user.setPoints(pointSet);
        when(userService.findById(any(Long.class))).thenReturn(user);
        assertEquals(pointService.findAllByUserId(1L), pointList);
    }

    @Test
    public void testFindById() {
        final Point point = new Point();
        when(pointRepository.findById(any(Long.class))).thenReturn(Optional.of(point));
        assertEquals(pointService.findById(1L), point);
    }

    @Test
    public void testSave() {
        final Point point = new Point();
        final Address address = new Address();
        address.setId(1L);
        point.setAddress(address);
        when(addressService.findById(any(Long.class))).thenReturn(address);
        when(pointRepository.saveAndFlush(point)).thenReturn(point);
        assertEquals(pointService.save(point), point);
    }

    @Test
    public void testUpdate() {
        final Point point = new Point();
        final Address address = new Address();
        address.setId(1L);
        point.setAddress(address);
        point.setId(1L);
        when(addressService.findById(any(Long.class))).thenReturn(address);
        ;
        when(pointRepository.saveAndFlush(point)).thenReturn(point);
        assertEquals(pointService.update(point), point);
    }

    @Test
    public void testDelete() {
        final Point point = new Point();
        point.setId(1L);
        when(pointRepository.findById(1L)).thenReturn(Optional.of(point));
        doNothing().when(pointRepository).delete(point);
        assertDoesNotThrow(() -> pointService.delete(point));
    }

    @Test
    public void testDeleteById() {
        final Point point = new Point();
        point.setId(1L);
        doNothing().when(pointRepository).deleteById(any(Long.class));
        when(pointRepository.findById(1L)).thenReturn(Optional.of(point));
        assertDoesNotThrow(() -> pointService.deleteById(1L));
    }
}
