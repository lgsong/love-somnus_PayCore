package com.somnus.pay.core.support.exceptions;

import com.somnus.pay.core.support.common.MsgCodeList;

public class PayException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	private String errorMsg;

	public PayException() {
		super();
	}
	
	public PayException(String message) {
		super(message);
		createErrorMsg(message);
	}

	public PayException(String message, Throwable cause) {
		super(message, cause);
		createErrorMsg(message);
	}

	public PayException(Throwable cause) {
		super(cause);
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
	
	private void createErrorMsg(String message){
		if (message != null && message.split("[|]").length > 1) {
			String[] errs = message.split("[|]");
			// 错误信息设置
			errorCode = errs[0];
			errorMsg = errs[1];
		} else {
			// 错误信息设置
			errorCode = MsgCodeList.ERROR_999999;
			errorMsg = message;
		}
	}
    
}
