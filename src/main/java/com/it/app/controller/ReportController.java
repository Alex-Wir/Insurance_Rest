package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.ReportYearDto;
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

    /**
     * find insurances by year and calculate sum and quantity
     *
     * @param year - year
     * @return
     */
    @RequestMapping(value = "/{year}", method = RequestMethod.GET)
    public ResponseEntity<ReportYearDto> getOne(@PathVariable Long year) {
        //final ReportYearDto reportDto = mapper.map(reportService.makeByYear(year), ReportYearDto.class);
        final ReportYearDto reportDto = reportService.makeByYear(year);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }
}
