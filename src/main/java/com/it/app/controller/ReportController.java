package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.ReportDto;
import com.it.app.service.PointService;
import com.it.app.service.ReportService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final Mapper mapper;
    private final LocalizedMessageSource localizedMessageSource;
    private final ReportService reportService;
    private final PointService pointService;

    /**
     * find insurances by year. Calculate sum of payments and quantity of insurances
     *
     * @param year - year
     * @return
     */
    @RequestMapping(value = "/{year}", method = RequestMethod.GET)
    public ResponseEntity<ReportDto> makeForYear(@PathVariable Long year) {
        final ReportDto reportDto = reportService.makeForYear(year);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    /**
     * find insurances by year and point id. Calculate sum of payments and quantity of insurances
     *
     * @param year    - year
     * @param pointId - point id
     * @return
     */
    @RequestMapping(value = "/{year}/points/{pointId}", method = RequestMethod.GET)
    public ResponseEntity<ReportDto> makeForYearAndPoint(@PathVariable Long year, @PathVariable Long pointId) {
        pointService.findById(pointId);
        final ReportDto reportDto = reportService.makeForYearAndPoint(year, pointId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

}
