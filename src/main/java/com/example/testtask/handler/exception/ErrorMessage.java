package com.example.testtask.handler.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * Error message for handled exceptions that will be send to client.
 */
@Value
@Builder
public class ErrorMessage {

  HttpStatus statusCode;

  LocalDateTime dateTime;

  String description;

  String url;

}
