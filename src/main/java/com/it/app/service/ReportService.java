package com.it.app.service;

import com.it.app.dto.ReportDto;

public interface ReportService {

    ReportDto makeForYear(Long year);

    ReportDto makeForYearAndPoint(Long year, Long pointId);

    ReportDto makeForPeriodAndPos(String periodFrom, String periodTo, Long posId);

    ReportDto makeForPeriodAndPoint(String periodFrom, String periodTo, Long pointId);
}
