package com.somnus.pay.core.pojo;

import java.io.Serializable;

public class ConfirmResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean support;
	
	private PaymentResult result;

	public ConfirmResult(){}
	
	public ConfirmResult(boolean support, PaymentResult result){
		this.support = support;
		this.result = result;
	}
	
	public boolean isSupport() {
		return support;
	}

	public void setSupport(boolean support) {
		this.support = support;
	}

	public PaymentResult getResult() {
		return result;
	}

	public void setResult(PaymentResult result) {
		this.result = result;
	}

}
