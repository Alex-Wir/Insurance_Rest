package com.it.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Response DTO class including error status and message
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private HttpStatus httpStatus;
    private String message;

}
