package com.somnus.pay.core.service;

import com.somnus.pay.core.pojo.PaymentOrder;
import com.somnus.pay.core.pojo.PaymentResult;

/**
 * @ClassName:     PaymentService.java
 * @Description:   支付服务(不带事务控制)
 * @author         Somnus
 * @version        V1.0  
 * @Date           2016年12月28日 下午4:15:50
 */
public interface PaymentService {

	/**
	 * 支付核心处理逻辑
	 * @param paymentOrder
	 * @return
	 */
	public String pay(PaymentOrder paymentOrder);
	
	/**
	 * 确认并更新订单的支付结果
	 * @param orderId
	 * @return
	 */
	public boolean confirmAndUpdateOrder(String orderId);
	
	/**
	 * 更新订单为支付成功
	 * @param paymentResult
	 */
	public void updateOrder2Success(PaymentResult paymentResult);


}
