package com.alvegag.transcendence.dto;

public class LoginResponse {

  private String message;
  private boolean success;

  public String getMessage() {
    return message;
  }

  public boolean getSuccess() {
    return success;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

}
