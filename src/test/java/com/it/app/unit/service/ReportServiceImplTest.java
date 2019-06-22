package com.it.app.unit.service;

import com.it.app.dto.ReportDto;
import com.it.app.dto.response.InsuranceResponseDto;
import com.it.app.model.Insurance;
import com.it.app.service.impl.InsuranceServiceImpl;
import com.it.app.service.impl.ReportServiceImpl;
import org.dozer.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private InsuranceServiceImpl insuranceService;

    @Mock
    private Mapper mapper;

    private Insurance insurance;
    private List<Insurance> insurances;
    private InsuranceResponseDto insuranceDto;
    private List<InsuranceResponseDto> insurancesDto;
    private ReportDto report;

    @BeforeEach
    public void initialize() {
        insurance = new Insurance();
        insurance.setPayment(1F);
        insurances = Collections.singletonList(insurance);
        insuranceDto = new InsuranceResponseDto();
        insuranceDto.setPayment(1F);
        insurancesDto = Collections.singletonList(insuranceDto);
        report = new ReportDto();
        report.setInsurances(insurancesDto);
        report.setQuantity(1);
        report.setSum(1);
        when(mapper.map(insurance, InsuranceResponseDto.class)).thenReturn(insuranceDto);
    }

    @Test
    public void testMakeForYear() {
        when(insuranceService.findAllByYear(any(Long.class))).thenReturn(insurances);
        assertEquals(reportService.makeForYear(1L), report);
    }

    @Test
    public void testMakeForYearAndPoint() {
        when(insuranceService.findAllByPeriodAndPoint(any(String.class), any(String.class), any(Long.class)))
                .thenReturn(insurances);
        assertEquals(reportService.makeForYearAndPoint(1L, 1L), report);
    }

    @Test
    public void testMakeForPeriodAndPos() {
        when(insuranceService.findAllByPeriodAndPos(any(String.class), any(String.class), any(Long.class)))
                .thenReturn(insurances);
        assertEquals(reportService.makeForPeriodAndPos("a", "b", 1L), report);
    }

    @Test
    public void testMkeForPeriodAndPoint() {
        when(insuranceService.findAllByPeriodAndPoint(any(String.class), any(String.class), any(Long.class)))
                .thenReturn(insurances);
        assertEquals(reportService.makeForPeriodAndPoint("a", "b", 1L), report);
    }

}
