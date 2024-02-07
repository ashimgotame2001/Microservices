package org.example.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
