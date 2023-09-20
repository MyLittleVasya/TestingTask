package com.example.testtask.handler.exception;

import java.io.Serial;

/**
 * Exception that thrown when trying to interact with entity
 * which doesn`t exist.
 */
public class NotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -2597235591808671328L;

  public NotFoundException(String message) {
    super(message);
  }

}
