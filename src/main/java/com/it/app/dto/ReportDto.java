package com.it.app.dto;

import com.it.app.dto.response.InsuranceResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Response DTO class for Report
 */
@Data
public class ReportDto {

    private long quantity;
    private float sum;
    private List<InsuranceResponseDto> insurances;
}
