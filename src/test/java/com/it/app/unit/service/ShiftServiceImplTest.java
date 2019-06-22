package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Pos;
import com.it.app.model.Shift;
import com.it.app.model.User;
import com.it.app.repository.ShiftRepository;
import com.it.app.service.impl.PosServiceImpl;
import com.it.app.service.impl.ShiftServiceImpl;
import com.it.app.service.impl.UserServiceImpl;
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
public class ShiftServiceImplTest {

    @InjectMocks
    private ShiftServiceImpl shiftService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private ShiftRepository shiftRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private PosServiceImpl posService;

    @Test
    public void testFindAll() {
        final List<Shift> shiftList = Collections.singletonList(new Shift());
        when(shiftRepository.findAll()).thenReturn(shiftList);
        assertEquals(shiftService.findAll(), shiftList);
    }

    @Test
    public void testfindAllByUserId() {
        final List<Shift> shiftList = Collections.singletonList(new Shift());
        when(shiftRepository.findShiftsByUserId(any(Long.class))).thenReturn(shiftList);
        assertEquals(shiftService.findAllByUserId(1L), shiftList);
    }

    @Test
    public void testFindById() {
        final Shift shift = new Shift();
        when(shiftRepository.findById(any(Long.class))).thenReturn(Optional.of(shift));
        assertEquals(shiftService.findById(1L), shift);
    }

    @Test
    public void testSave() {
        final Shift shift = new Shift();
        User user = new User();
        user.setId(1L);
        shift.setUser(user);
        Pos pos = new Pos();
        pos.setId(1L);
        shift.setPos(pos);
        when(userService.findById(any(Long.class))).thenReturn(user);
        when(posService.findById(any(Long.class))).thenReturn(pos);
        when(shiftRepository.saveAndFlush(shift)).thenReturn(shift);
        assertEquals(shiftService.save(shift), shift);
    }

    @Test
    public void testUpdate() {
        final Shift shift = new Shift();
        shift.setId(1L);
        User user = new User();
        user.setId(1L);
        shift.setUser(user);
        Pos pos = new Pos();
        pos.setId(1L);
        shift.setPos(pos);
        when(userService.findById(any(Long.class))).thenReturn(user);
        when(posService.findById(any(Long.class))).thenReturn(pos);
        when(shiftRepository.saveAndFlush(shift)).thenReturn(shift);
        assertEquals(shiftService.update(shift), shift);
    }

    @Test
    public void testDelete() {
        final Shift shift = new Shift();
        shift.setId(1L);
        when(shiftRepository.findById(1L)).thenReturn(Optional.of(shift));
        doNothing().when(shiftRepository).delete(shift);
        assertDoesNotThrow(() -> shiftService.delete(shift));
    }

    @Test
    public void testDeleteById() {
        final Shift shift = new Shift();
        shift.setId(1L);
        doNothing().when(shiftRepository).deleteById(any(Long.class));
        when(shiftRepository.findById(1L)).thenReturn(Optional.of(shift));
        assertDoesNotThrow(() -> shiftService.deleteById(1L));
    }

}
