package com.yinyang.yy.common.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultDto<T>
  implements Serializable
{
  private static final long serialVersionUID = -1675577403L;
  private String systemErrorCode;
  private String systemErrorMessage;
  private T data;
  private List<ResultDtoErr> paramErrorList = new ArrayList();
  private List<ResultDtoErr> businessErrorList = new ArrayList();

  public void paramError(String errorCode, String errorMessage)
  {
    if (checkCodeNull(errorCode, errorMessage)) {
      ResultDtoErr error = new ResultDtoErr(errorCode, errorMessage);
      this.paramErrorList.add(error);
    }
  }

  public void paramError(String errorCode, String errorMessage, Object[] obj)
  {
    if (checkCodeNull(errorCode, errorMessage)) {
      ResultDtoErr error = new ResultDtoErr(errorCode, errorMessage, obj);
      this.paramErrorList.add(error);
    }
  }

  public void businessError(String errorCode, String errorMessage)
  {
    if (checkCodeNull(errorCode, errorMessage)) {
      ResultDtoErr error = new ResultDtoErr(errorCode, errorMessage);
      this.businessErrorList.add(error);
    }
  }

  public void businessError(String errorCode, String errorMessage, Object[] obj) {
    if (checkCodeNull(errorCode, errorMessage)) {
      ResultDtoErr error = new ResultDtoErr(errorCode, errorMessage, obj);
      this.businessErrorList.add(error);
    }
  }

  public void systemError(String errorCode, String errorMessage) {
    if (checkCodeNull(errorCode, errorMessage)) {
      this.systemErrorCode = errorCode;
      this.systemErrorMessage = ((errorMessage == null) ? "" : errorMessage);
    }
  }

  private boolean checkCodeNull(String errorCode, String errorMessage)
  {
    return ((errorCode != null) && (!("".equals(errorCode.trim()))));
  }

  public void data(T data)
  {
    this.data = data;
  }

  public boolean isParamError() {
    return (this.paramErrorList.size() > 0);
  }

  public boolean isBusinessError() {
    return (this.businessErrorList.size() > 0);
  }

  public boolean isSystemError() {
    return ((this.systemErrorCode != null) && (this.systemErrorMessage != null));
  }

  public String getSystemErrCode() {
    return this.systemErrorCode;
  }

  public String getSystemErrMessage() {
    return this.systemErrorMessage;
  }

  public List<ResultDtoErr> getParamErrList() {
    return this.paramErrorList;
  }

  public List<ResultDtoErr> getBusinessErrList() {
    return this.businessErrorList;
  }

  public T getData()
  {
    return this.data;
  }

  public boolean isAck()
  {
    return ((!(isParamError())) && (!(isBusinessError())) && (!(isSystemError())));
  }

  public String getVersion() {
    return "1.2";
  }
}