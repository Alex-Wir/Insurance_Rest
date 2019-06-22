package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Point;
import com.it.app.model.Pos;
import com.it.app.repository.PosRepository;
import com.it.app.service.impl.PointServiceImpl;
import com.it.app.service.impl.PosServiceImpl;
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
public class PosServiceImplTest {

    @InjectMocks
    private PosServiceImpl posService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private PosRepository posRepository;

    @Mock
    private PointServiceImpl pointService;


    @Test
    public void testFindAll() {
        final List<Pos> posList = Collections.singletonList(new Pos());
        when(posRepository.findAll()).thenReturn(posList);
        assertEquals(posService.findAll(), posList);
    }


    @Test
    public void testFindById() {
        final Pos pos = new Pos();
        when(posRepository.findById(any(Long.class))).thenReturn(Optional.of(pos));
        assertEquals(posService.findById(1L), pos);
    }

    @Test
    public void testSave() {
        final Pos pos = new Pos();
        pos.setName("1234");
        Point point = new Point();
        point.setId(1L);
        pos.setPoint(point);
        when(pointService.findById(any(Long.class))).thenReturn(point);
        when(posRepository.existsByName(any(String.class))).thenReturn(false);
        when(posRepository.saveAndFlush(pos)).thenReturn(pos);
        assertEquals(posService.save(pos), pos);
    }

    @Test
    public void testUpdate() {
        final Pos pos = new Pos();
        pos.setId(1L);
        when(posRepository.saveAndFlush(pos)).thenReturn(pos);
        assertEquals(posService.update(pos), pos);
    }

    @Test
    public void testDelete() {
        final Pos pos = new Pos();
        pos.setId(1L);
        when(posRepository.findById(1L)).thenReturn(Optional.of(pos));
        doNothing().when(posRepository).delete(pos);
        assertDoesNotThrow(() -> posService.delete(pos));
    }

    @Test
    public void testDeleteById() {
        final Pos pos = new Pos();
        pos.setId(1L);
        doNothing().when(posRepository).deleteById(any(Long.class));
        when(posRepository.findById(1L)).thenReturn(Optional.of(pos));
        assertDoesNotThrow(() -> posService.deleteById(1L));
    }
}
