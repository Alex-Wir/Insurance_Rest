package com.it.app.service.impl;

import com.it.app.dto.ReportDto;
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

    private final InsuranceService insuranceService;
    private final Mapper mapper;

    @Override
    public ReportDto makeForYear(Long year) {
        return makeReport(insuranceService.findAllByYear(year));
    }

    @Override
    public ReportDto makeForYearAndPoint(Long year, Long pointId) {
        String yearString = year.toString();
        String yearBegin = yearString.concat("-01-01");
        String yearEnd = yearString.concat("-12-31");
        return makeReport(insuranceService.findAllByPeriodAndPoint(yearBegin, yearEnd, pointId));
    }

    @Override
    public ReportDto makeForPeriodAndPos(String periodFrom, String periodTo, Long posId) {
        return makeReport(insuranceService.findAllByPeriodAndPos(periodFrom, periodTo, posId));
    }

    @Override
    public ReportDto makeForPeriodAndPoint(String periodFrom, String periodTo, Long pointId) {
        return makeReport(insuranceService.findAllByPeriodAndPoint(periodFrom, periodTo, pointId));
    }

    private ReportDto makeReport(List<Insurance> insurances) {
        final ReportDto report = new ReportDto();
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        report.setInsurances(insuranceResponseDtoList);
        report.setQuantity(insuranceResponseDtoList.size());
        float sum = 0;
        for (InsuranceResponseDto insurance : report.getInsurances()) {
            sum += insurance.getPayment();
        }
        report.setSum(sum);
        return report;
    }
}
