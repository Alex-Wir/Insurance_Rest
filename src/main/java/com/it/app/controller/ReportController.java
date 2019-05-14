package com.it.app.controller;

import com.it.app.dto.ReportDto;
import com.it.app.service.PointService;
import com.it.app.service.PosService;
import com.it.app.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Report controller
 */
@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final PointService pointService;
    private final PosService posService;

    /**
     * Find all Insurances by year. Calculate sum of payments and quantity of Insurances
     *
     * @param year - year
     * @return - ResponseEntity with ReportDto and HttpStatus
     */
    @GetMapping(value = "/{year}")
    public ResponseEntity<ReportDto> makeForYear(@PathVariable Long year) {
        final ReportDto reportDto = reportService.makeForYear(year);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    /**
     * Find all Insurances by year and Point id. Calculate sum of payments and quantity of Insurances
     *
     * @param year    - year
     * @param pointId - Point id
     * @return - ResponseEntity with ReportDto and HttpStatus
     */
    @GetMapping(value = "/{year}/points/{pointId}")
    public ResponseEntity<ReportDto> makeForYearAndPoint(@PathVariable Long year, @PathVariable Long pointId) {
        pointService.findById(pointId);
        final ReportDto reportDto = reportService.makeForYearAndPoint(year, pointId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    /**
     * Find all Insurances for the period and Pos id. Calculate sum of payments and quantity of Insurances
     *
     * @param posId - Pos id
     * @param from  - beginning of period
     * @param to    - end of period
     * @return - ResponseEntity with ReportDto and HttpStatus
     */
    @GetMapping("/poses/{posId}")
    public ResponseEntity<ReportDto> makeForPeriodAndPos(@PathVariable Long posId,
                                                         @RequestParam String from, @RequestParam String to) {
        posService.findById(posId);
        final ReportDto reportDto = reportService.makeForPeriodAndPos(from, to, posId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    /**
     * Find all Insurances for the period and Point id. Calculate sum of payments and quantity of Insurances
     *
     * @param pointId - Point id
     * @param from    - beginning of period
     * @param to      - end of period
     * @return - ResponseEntity with ReportDto and HttpStatus
     */
    @GetMapping("/points/{pointId}")
    public ResponseEntity<ReportDto> makeForPeriodAndPoint(@PathVariable Long pointId,
                                                           @RequestParam String from, @RequestParam String to) {
        pointService.findById(pointId);
        final ReportDto reportDto = reportService.makeForPeriodAndPoint(from, to, pointId);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }
}
