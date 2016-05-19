package com.yinyang.yy.common.metadata;

import java.io.Serializable;

public class ResultDtoErr
  implements Serializable
{
  private static final long serialVersionUID = 433995269L;
  private String errorCode;
  private String errorMessage;
  private Object[] objMessage;

  public ResultDtoErr(String errorCode, String errorMessage)
  {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage; }

  public ResultDtoErr(String errorCode, String errorMessage, Object[] obj) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.objMessage = obj;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public Object[] getObjMessage()
  {
    return this.objMessage;
  }
}