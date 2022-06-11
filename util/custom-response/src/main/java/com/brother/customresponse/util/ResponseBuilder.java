package com.brother.customresponse.util;


import com.brother.customresponse.payload.ErrorResponseDto;
import com.brother.customresponse.payload.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class ResponseBuilder {
    private ResponseBuilder() {
    }

    private static List<ErrorResponseDto> getCustomError(BindingResult result) {
        List<ErrorResponseDto> dtoList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            dtoList.add(errorResponseDto);
        });
        return dtoList;
    }

    public static Response getFailureResponse(BindingResult result, String message) {
        return Response.builder()
                .message(message)
                .errors(getCustomError(result))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static Response getFailureResponse(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, Long numberOfElement) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(LocalDateTime.now())
                .numberOfElement(numberOfElement)
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, Long numberOfElement,
                                              Long rowCount) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(LocalDateTime.now())
                .numberOfElement(numberOfElement)
                .rowCount(rowCount)
                .build();
    }
}
