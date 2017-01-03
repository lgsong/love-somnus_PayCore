package com.somnus.pay.core.service;

import java.util.List;
import java.util.Map;

import com.somnus.pay.core.enums.PayChannel;
import com.somnus.pay.core.pojo.Page;
import com.somnus.pay.core.pojo.QueryResult;
import com.somnus.pay.core.pojo.Switch;
import com.somnus.pay.core.thirdpay.RequestType;

public interface PaymentChannelSwitchService {

	public void toggle(PayChannel channel, RequestType type);
	
	public void toggle(String key);
	
	public void setValue(PayChannel channel, RequestType type, boolean value);
	
	public boolean getValue(PayChannel channel, RequestType type);
	
	public List<Switch> getAll();
	
	public QueryResult<Switch> list(Page page);

	public QueryResult<Switch> list(Page page, Map<String, Object> params);

}
