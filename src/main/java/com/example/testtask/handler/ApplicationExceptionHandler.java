package com.example.testtask.handler;

import com.example.testtask.handler.exception.AlreadyExistsException;
import com.example.testtask.handler.exception.ErrorMessage;
import com.example.testtask.handler.exception.NotFoundException;
import com.example.testtask.handler.exception.OperationNotAllowedException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler that handles exception and sends
 * expected response body to client.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


  /**
   * Handle NotFound exception.
   *
   * @param request request send response to.
   * @param exception exception to be handled.
   * @return response entity with structured error message.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .statusCode(HttpStatus.NOT_FOUND)
        .dateTime(LocalDateTime.now())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }


  /**
   * Handle OperationNotAllowed exception.
   *
   * @param request request send response to.
   * @param exception exception to be handled.
   * @return response entity with structured error message.
   */
  @ExceptionHandler(OperationNotAllowedException.class)
  public ResponseEntity<ErrorMessage> handleOperationNowAllowedException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) {
    final var message = ErrorMessage.builder()
        .statusCode(HttpStatus.BAD_REQUEST)
        .dateTime(LocalDateTime.now())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }


  /**
   * Handle AlreadyExistsException exception.
   *
   * @param request request send response to.
   * @param exception exception to be handled.
   * @return response entity with structured error message.
   */
  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ErrorMessage> handleAlreadyExistsException(
      @Nonnull final HttpServletRequest request,
      @Nonnull final Exception exception) { //
    final var message = ErrorMessage.builder()
        .statusCode(HttpStatus.BAD_REQUEST)
        .dateTime(LocalDateTime.now())
        .description(exception.getMessage())
        .url(request.getRequestURL().toString())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }
}
