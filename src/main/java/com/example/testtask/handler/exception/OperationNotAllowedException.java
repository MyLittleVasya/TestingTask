package com.example.testtask.handler.exception;

import java.io.Serial;

/**
 * Exception that is thrown when trying to do operation
 * that you cannot perform.
 */
public class OperationNotAllowedException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -546292212789512331L;

  public OperationNotAllowedException(String message) {
    super(message);
  }
}
