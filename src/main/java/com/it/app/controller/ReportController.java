package com.it.app.controller;

import com.it.app.dto.ReportDto;
import com.it.app.service.PointService;
import com.it.app.service.PosService;
import com.it.app.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final PointService pointService;
    private final PosService posService;

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

    /**
     * find insurances for the period and pos id. Calculate sum of payments and quantity of insurances
     *
     * @param posId - pos id
     * @param from  - beginning of period
     * @param to    - end of period
     * @return
     */
    @GetMapping("/poses/{posId}")
    public ResponseEntity<ReportDto> makeForPeriodAndPos(@PathVariable Long posId,
                                                         @RequestParam String from, @RequestParam String to) {
        posService.findById(posId);
        final ReportDto reportDto = reportService.makeForPeriodAndPos(from, to, posId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

}
