package com.it.app.dto;

import com.it.app.dto.response.InsuranceResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDto {

    private long quantity;
    private float Sum;
    private List<InsuranceResponseDto> insurances;
}
