package org.example.exceptions;

import lombok.*;

import java.net.URI;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String detail;
    private String message;
    private String errorKey;
    private URI path;
}
