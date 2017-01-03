package com.somnus.pay.core.support.common;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;

import com.somnus.pay.core.support.exceptions.PayException;
import com.somnus.pay.core.support.holder.ApplicationContextHolder;
import com.somnus.pay.core.support.util.ValidateUtil;

public class Assert {
	
	/**
	 * @Title:isEmpty
	 * @Description:报文为空校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isEmpty(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301001, new Object[]{message}));
		}
	}

	/**
	 * @Title:isEmpty
	 * @Description:集合为空校验
	 * @param list
	 * @param name
	 * @return void
	 */
	public static <T> void isEmpty(List<T> list) {
		if (list == null || list.size() == 0) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301014));
		}
	}

	/**
	 * @Title:isNull
	 * @Description:为空校验
	 * @param obj
	 * @param name
	 * @return void
	 */
	public static void isNull(Object obj, String message) {
		if (obj == null) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_300000, new Object[]{message}));
		}
	}
	
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_300000, new Object[]{message}));
		}
	}

	/**
	 * @Title:isDecimalNumber
	 * @Description:报文金额校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isDecimalNumber(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		if (!ValidateUtil.isAmt(src)) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301009, new Object[]{message}));
		}
	}

	/**
	 * @Title:isRightDate
	 * @Description:报文日期校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isRightDate(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		if (!ValidateUtil.isValidDateTime(src)) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301003, new Object[]{message, "yyyy-MM-dd HH:mm:ss"}));
		}
	}

	/**
	 * @Title:isRightDay
	 * @Description:报文日期校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isRightDay(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		if (!ValidateUtil.isDate(src, "yyyy-MM-dd")) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301003, new Object[]{message, "yyyy-MM-dd"}));
		}
	}

	/**
	 * @Title:isRightTime
	 * @Description:时间校验
	 * @param time
	 * @param name
	 * @return void
	 */
	public static void isRightTime(String time, String message) {
		if (ValidateUtil.isEmpty(time)) {
			return;
		}
		if (!ValidateUtil.isValidTime(time)) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301003, new Object[]{message, "HH:mm:ss"}));
		}
	}

	/**
	 * @Title:isInterger
	 * @Description:报文整数校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isInterger(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[1-9][0-9]*");
		java.util.regex.Matcher match = pattern.matcher(src);
		if (!match.matches()) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301002, new Object[]{message}));
		}
	}
	
	/**
	 * @Title:isInterger
	 * @Description:报文正整数校验
	 * @param src
	 * @param name
	 * @return void
	 */
	public static void isPositiveInterger(String src, String message) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[1-9][0-9]*");
		java.util.regex.Matcher match = pattern.matcher(src);
		if (!match.matches()) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301012, new Object[]{message}));
		}
	}

	/**
	 * @Title:isGreatLenth
	 * @Description:报文长度
	 * @param src
	 * @param name
	 * @param length
	 * @return void
	 */
	public static void isGreatLenth(String src, String message, int length) {
		if (ValidateUtil.isEmpty(src)) {
			return;
		}
		if (ValidateUtil.isGreatLenth(src, length)) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301005, new Object[]{message, length}));
		}
	}

	/**
	 * @Title:isGreatLenth
	 * @Description:报文长度
	 * @param src
	 * @param name
	 * @param length
	 * @return void
	 */
	public static void isBigthanZero(String amt, String amtname) {
		if (!ValidateUtil.isEmpty(amt)
				&& new BigDecimal(amt).compareTo(new BigDecimal("0")) < 0) {
			throw new PayException(msa.getMessage(MsgCodeList.ERROR_301010, new Object[]{amtname}));
		}
	}

	
	private static MessageSourceAccessor msa = ApplicationContextHolder.getBean(MessageSourceAccessor.class);
}
