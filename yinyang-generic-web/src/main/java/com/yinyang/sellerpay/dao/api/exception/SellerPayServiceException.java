package com.yinyang.sellerpay.dao.api.exception;

/**
 * 通用异常类
 * 
 * @Title: SellerPayServiceException.java
 * @Package com.dhgate.sellerpay.exception
 * @author yinyang@dhgate.com
 * @date 2015年12月21日 下午3:59:53
 * @version V1.0
 */
public class SellerPayServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMsg;

	public SellerPayServiceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SellerPayServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    public SellerPayServiceException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    
    public SellerPayServiceException(String errorCode,String errorMsg) {
        super(errorCode+"-"+errorMsg);
        this.errorCode = errorCode;
    	this.errorMsg = errorMsg;
        // TODO Auto-generated constructor stub
    }

    public SellerPayServiceException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
