package com.example.testtask.handler.exception;

import java.io.Serial;

/**
 * Exception thrown when trying to create entity that alreadt exists.
 */
public class AlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 3495314523711822253L;

  public AlreadyExistsException(String message) {
    super(message);
  }
}
