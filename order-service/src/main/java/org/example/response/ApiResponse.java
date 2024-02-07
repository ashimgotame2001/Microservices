package org.example.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {
    private Integer statusCode;
    private String message;
}
