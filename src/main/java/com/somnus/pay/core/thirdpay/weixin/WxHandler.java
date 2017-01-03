package com.somnus.pay.core.thirdpay.weixin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.somnus.pay.core.enums.PayChannel;
import com.somnus.pay.core.support.util.WebUtil;
import com.somnus.pay.core.thirdpay.RequestParameter;
import com.somnus.pay.core.thirdpay.weixin.config.WxConfig;
import com.somnus.pay.core.thirdpay.weixin.util.WxUtil;
import com.somnus.pay.core.thirdpay.weixin.util.XMLUtil;
import com.somnus.pay.core.pojo.PaymentOrder;

@Service     
public class WxHandler extends AbstractWxHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(WxHandler.class);
	
	public WxHandler() {
		super(PayChannel.WxPay, "", "", "", "");
	}

	@Override
	public String handleOrder(RequestParameter<PaymentOrder, String> parameter) {
		if(LOGGER.isInfoEnabled()){
            LOGGER.info("创建微信PC端二维码支付的请求参数:[{}]", parameter.getParameter());
        }
        HttpServletRequest request = WebUtil.getRequest();
        String requestXML = transRequestWxPara2Xml(parameter.getParameter(),request, false);
        Map<String, String> map = null;
		try {
			String result = Request.Post(WxConfig.UNIFIED_ORDER_URL).bodyString(requestXML,ContentType.APPLICATION_XML).execute().returnContent().asString();
			map = XMLUtil.doXMLParse(result);
			String returnCode = map.get("return_code");
			String resultCode = map.get("result_code");
			if (returnCode.equalsIgnoreCase("SUCCESS") && resultCode.equalsIgnoreCase("SUCCESS")) {
				return Request.Post(WxUtil.getRootPath(request) + "/third/wxcode.htm").bodyForm(Form.form()
						.add("finalAmount", parameter.getParameter().getAmount().toString())
						.add("orderId", parameter.getParameter().getOrderId())
						.build())
						.execute().returnContent().asString();
			}else if(returnCode.equalsIgnoreCase("SUCCESS") && !resultCode.equalsIgnoreCase("SUCCESS")){
				return returnCode + "" + map.get("err_code_des") ;
			}
			return map.get("return_code") + map.get("return_msg");
		} catch (Exception e) {
			LOGGER.warn("创建微信PC端二维码支付的请求参数失败", e);
		}
		return null;
	}

}
