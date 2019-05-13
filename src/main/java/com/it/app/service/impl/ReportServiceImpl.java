package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.ReportYearDto;
import com.it.app.dto.response.InsuranceResponseDto;
import com.it.app.model.Insurance;
import com.it.app.service.InsuranceService;
import com.it.app.service.ReportService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final LocalizedMessageSource localizedMessageSource;
    private final InsuranceService insuranceService;
    private final Mapper mapper;

    @Override
    public ReportYearDto makeByYear(Long year) {
        final ReportYearDto report = new ReportYearDto();
        final List<Insurance> insurances = insuranceService.findAllByYear(year);
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        report.setInsurances(insuranceResponseDtoList);
        report.setQuantity(report.getInsurances().size());
        float sum = 0;
        for (InsuranceResponseDto insurance:report.getInsurances()){
            sum += insurance.getPayment();
        }
        report.setSum(sum);
        return report;
    }
}
