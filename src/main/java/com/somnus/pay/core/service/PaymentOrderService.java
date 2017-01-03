package com.somnus.pay.core.service;

import java.util.Map;

import com.somnus.pay.core.pojo.Page;
import com.somnus.pay.core.pojo.PaymentOrder;
import com.somnus.pay.core.pojo.PaymentResult;
import com.somnus.pay.core.pojo.QueryResult;

public interface PaymentOrderService {

	public PaymentOrder get(String orderId, int source);
	
	public PaymentOrder get(String orderId);
	
	public PaymentResult convert(PaymentOrder paymentOrder);

	public QueryResult<PaymentOrder> list(Page page,Map<String,Object> params);
	
	public PaymentOrder get(String orderId, String userId);
	
}
