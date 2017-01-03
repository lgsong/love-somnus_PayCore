package com.somnus.pay.core.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.somnus.pay.core.enums.PayChannel;
import com.somnus.pay.core.pojo.ConfirmResult;
import com.somnus.pay.core.pojo.PaymentOrder;
import com.somnus.pay.core.pojo.PaymentResult;
import com.somnus.pay.core.service.PaymentChannelService;
import com.somnus.pay.core.support.common.Assert;
import com.somnus.pay.core.thirdpay.PaymentChannelHandler;
import com.somnus.pay.core.thirdpay.PaymentChannelHandlerAdapter;
import com.somnus.pay.core.thirdpay.RequestParameter;
import com.somnus.pay.core.thirdpay.RequestType;

@Service
public class PaymentChannelServiceImpl implements PaymentChannelService {

	private final static Logger LOGGER = LoggerFactory.getLogger(PaymentChannelServiceImpl.class);
	
	@Resource
	private PaymentChannelHandlerAdapter handlerAdapter;
	
	protected PaymentChannelHandler getHandler(RequestParameter<?, ?> parameter) {
		LOGGER.info("处理支付请求:{}", parameter);
		Assert.isNull(parameter.getChannel(), "PAY_CHANNEL_IS_NULL");
		Assert.isNull(parameter.getType(), "REQUEST_TYPE_IS_NULL");
		Assert.isNull(parameter.getParameter(), "PAYMENT_PARAMETER_IS_NULL");
		PaymentChannelHandler handler = handlerAdapter.getHandler(parameter);
		Assert.isNull(handler, "PAYMENT_HANDLER_NOT_FOUND");
		return handler;
	}
	
	private String handleCallback(RequestParameter<?, String> parameter) {
		PaymentChannelHandler handler = this.getHandler(parameter);
		try {
			return handler.handle(parameter);
		} catch (Exception e) {
			LOGGER.warn("处理支付前端回调请求失败", e);
		}
		return handler.getFailedResponse(parameter);
	}

	@Override
	public String createOrder(PaymentOrder paymentOrder) {
		RequestParameter<PaymentOrder, String> parameter = new RequestParameter<PaymentOrder, String>(PayChannel.valueOf(paymentOrder.getThirdPayType()), RequestType.ORDER, paymentOrder);
		PaymentChannelHandler handler = this.getHandler(parameter);
		return handler.handle(parameter);
	}

	@Override
	public String handleReturn(PayChannel channel, Map<String, String> parameter) {
		return handleCallback(new RequestParameter<Map<String, String>, String>(channel, RequestType.RETURN, parameter));
	}

	@Override
	public String handleReturn(PayChannel channel, String parameter) {
		return handleCallback(new RequestParameter<String, String>(channel, RequestType.RETURN, parameter));
	}
	
	@Override
	public String handleNotify(PayChannel channel, Map<String, String> parameter) {
		return handleCallback(new RequestParameter<Map<String, String>, String>(channel, RequestType.NOTIFY, parameter));
	}

	@Override
	public String handleNotify(PayChannel channel, String parameter) {
		return handleCallback(new RequestParameter<String, String>(channel, RequestType.NOTIFY, parameter));
	}

	@Override
	public String handleRefund(PayChannel channel, Map<String, String> parameter) {
		return handleCallback(new RequestParameter<Map<String, String>, String>(channel, RequestType.REFUND, parameter));
	}
	
	@Override
	public Map<String, String> queryOrder(PaymentOrder paymentOrder) {
		RequestParameter<String, Map<String, String>> parameter = new RequestParameter<String, Map<String,String>>();
		parameter.setChannel(PayChannel.valueOf(paymentOrder.getThirdPayType()));
		parameter.setType(RequestType.QUERY);
		parameter.setData(paymentOrder.getOrderId());
		
		PaymentChannelHandler handler = this.getHandler(parameter);
		
		Map<String, String> result = handler.handle(parameter);
		
		LOGGER.info("订单[{}]支付结果:{}", paymentOrder.getOrderId(), result);
		
		return result;
	}

	@Override
	public ConfirmResult confirmOrder(PaymentOrder paymentOrder) {
		RequestParameter<String, PaymentResult> parameter = new RequestParameter<String, PaymentResult>();
		parameter.setChannel(PayChannel.valueOf(paymentOrder.getThirdPayType()));
		parameter.setType(RequestType.CONFIRM);
		parameter.setData(paymentOrder.getOrderId());
		
		PaymentChannelHandler handler = this.getHandler(parameter);
		
		boolean result = handler.handle(parameter);
		
		LOGGER.info("订单[{}]支付结果:{}", paymentOrder.getOrderId(), result);
		
		return new ConfirmResult(result, parameter.getResult());
	}

	
}
