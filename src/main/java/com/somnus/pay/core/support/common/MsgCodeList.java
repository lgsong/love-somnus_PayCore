package com.somnus.pay.core.support.common;

/**
 * 300xxx 系统错误 301xxx 报文错误 302xxx 日间支付异常 303xxx 日终异常
 */
public class MsgCodeList {
	/** 处理成功 */
	public static final String SUCCESS_000000 = "000000";
	
	/** 处理失败 */
	public static final String FAILED_222222 = "222222";
	
	/** 处理中 */
	public static final String HANDING_111111 = "111111";

	/** 系统异常 */
	public static final String ERROR_999999 = "999999";
	
	/** 系统错误：{0} */
	public static final String ERROR_300000 = "300000";

	/** 系统错误：系统配置文件{0}不存在！ */
	public static final String ERROR_300001 = "300001";

	/** 系统错误：ACTION返回报文实例为NULL！ */
	public static final String ERROR_300002 = "300002";

	/** 系统错误: 报文内容签名与签名字段不符合，报文被篡改！ */
	public static final String ERROR_300003 = "300003";

	/** 系统错误: 报文签名验证异常！ */
	public static final String ERROR_300004 = "300004";
	
	/** 系统错误:解密失败! */
	public static final String ERROR_300007 = "300007";

	/** 系统错误:{0}获取密钥失败! */
	public static final String ERROR_300008 = "300008";
	
	/** {0}不可为空！ */
	public static final String ERROR_301001 = "301001";

	/** {0}必须为数字或金额！ */
	public static final String ERROR_301002 = "301002";

	/** {0}格式必须为{1}！ */
	public static final String ERROR_301003 = "301003";

	/**{0}元素个数超过限制，最多{1}个！ */
	public static final String ERROR_301004 = "301004";

	/**{0}元素长度不正确！ */
	public static final String ERROR_301005 = "301005";
	
	/** 处理支付渠道的回调请求失败！ */
	public static final String ERROR_301006 = "301006";

	/** 操作请求类型错误！ */
	public static final String ERROR_301007 = "301007";
	
	/** {0}无效！  */
	public static final String ERROR_301008 = "301008";
	
	/** {0}必须为金额或格式不正确！ */
	public static final String ERROR_301009 = "301009";
	
	/** 发生额(tranamt)不可为负数！ */
	public static final String ERROR_301010 = "301010";

	/** {0}必须为整数！ */
	public static final String ERROR_301011 = "301011";
	
	/** {0}必须为正整数！ */
	public static final String ERROR_301012 = "301012";

	/** 支付异常:{0}不可为空！ */
	public static final String ERROR_301013 = "301013";
	
	/** 查询出的结果集为空 */
	public static final String ERROR_301014 = "301014";
	
	/** 解析微信支付回调参数(XML)失败*/
	public static final String ERROR_500000 = "500000";
	
	/** 查询微信订单支付结果失败*/
	public static final String ERROR_500001 = "500001";
	
	/** 微信oauth2授权失败*/
	public static final String ERROR_500002 = "500002";
}
