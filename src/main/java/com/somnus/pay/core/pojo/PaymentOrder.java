package com.somnus.pay.core.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.somnus.pay.core.enums.PaymentOrderType;

@Entity
@Table(name = "t_user_payment_order")
public class PaymentOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @NotBlank(message = "{payment.order_id}")
    @Column(name = "order_id", nullable = false)
    private String  orderId; // 订单号信息，用来支持多订单的方式,多个订单号使用","分割
    
    @NotBlank(message = "{payment.user_id}")
    @Column(name = "user_id", nullable = false, length = 50)
    private String  userId; // 用户UUID
    
    @NotBlank(message = "{payment.subject}")
    @Column(name = "subject", nullable = false, length = 255)
    private String  subject       = ""; // 提交主题
    
    @NotNull(message = "{payment.amount}")
    @Min(value = 0, message = "{payment.amount.size}")
    @Column(name = "amount")
    private BigDecimal  amount; // 订单总金额
    
    @Column(name = "final_amount")
    private BigDecimal  finalAmount; // 订单回传金额，支付平台回传
    
    @Column(name = "total_amount")
    private BigDecimal  totalAmount; // 订单总金额，包含混合支付帮豆金额数
    
    @Column(name = "status")
    private Integer status        = PaymentOrderType.NOTDONE.getValue(); // 支付状态
    
    @Column(name = "source")
    private Integer source; // 支付来源,用来却分是普通代码还是VIP支付，还是充值
    
    @Column(name = "third_pay_type")
    private Integer thirdPayType; // 支付平台类型
    
    @Column(name = "memo")
    private String  memo;
    
    @Column(name = "third_trade_no")
    private String  thirdTradeNo; // 第三方支付平台号，自定义
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date    createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date    updateTime; // 订单支付时间
    
    @Column(name = "bank_type")
    private String  defaultBank; // 支付选择的银行类型
    
    @Column(name = "bank_bill_no")
    private String  bankBillNo; // 银行对账单
    
    @Column(name = "inviter_id")
    private String  inviterId;
    
    @Column(name = "free_fee_type")
    private Integer freeFeeType; // VIP的类型、1黄金 2白金
    
    @Column(name = "parent_order_id")
    private String  parentOrderId = ""; // 合并付款的批量支付ID,默认""，即单个订单;xxxxx表示多个订单
    
    @Column(name = "traffic_source")
    private String  trafficSource; // 订单流量来源
    
    @Column(name = "fee")
    private BigDecimal fee; //运费
    
    @Column(name = "tax")
    private BigDecimal tax; //税款
    
    @Column(name = "goods_amount")
    private BigDecimal goodsAmount; //货款金额

    @Transient
    private String  sourceKey; // 校验Key
    @Transient
    private String  sign; // 订单校验签名
    @Transient
    private String  buyerId;
    @Transient
    private String  address;
    @Transient
    private String  mobileNum; // 充值的电话号码
    @Transient
    private String  chargeValue; // 手机充值的金额
    @Transient
    private Boolean isCombined    = false; // 是否合并付款 0：不是(默认)，1：是
    @Transient  
    private String  amountDetail  = ""; // 合并订单的详细价格信息,多个订单价格使用","分割
    @Transient
    private Integer isAllowAliPay = 0; // 是否可用支付宝支付 0：否(默认)，1：是
    @Transient
	private String code;	//微信用户授权后返回的code
    @Transient
    private String scene; // 用于支付场景
    @Transient
    private String payFrom; // 支付来源
    @Transient
    private String imei;
    @Transient
    private Integer crossPay = 0; //是否需要境外支付0为国内，1为境外（香港），2为境外（韩国）
    @Transient
    private String addressId; //用户中心地址Id
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;

    //报关需要下单时使用
    @Transient
    private String idCardName; //下单者身份证对应姓名
    @Transient
    private String idCard; //下单者身份证号
    @Transient
    private String addressInfo; //下单者地址信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getThirdPayType() {
        return thirdPayType;
    }

    public void setThirdPayType(Integer thirdPayType) {
        this.thirdPayType = thirdPayType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo;
    }

    public String getDefaultBank() {
        return defaultBank;
    }

    public void setDefaultBank(String bankType) {
        this.defaultBank = bankType;
    }

    public String getBankBillNo() {
        return bankBillNo;
    }

    public void setBankBillNo(String bankBillNo) {
        this.bankBillNo = bankBillNo;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public Integer getFreeFeeType() {
        return freeFeeType;
    }

    public void setFreeFeeType(Integer freeFeeType) {
        this.freeFeeType = freeFeeType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public Boolean getIsCombined() {
        return isCombined;
    }

    public void setIsCombined(Boolean isCombined) {
        this.isCombined = isCombined;
    }

    public String getAmountDetail() {
        return amountDetail;
    }

    public void setAmountDetail(String amountDetail) {
        this.amountDetail = amountDetail;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(String chargeValue) {
        this.chargeValue = chargeValue;
    }

    public String getTrafficSource() {
        return trafficSource;
    }

    public void setTrafficSource(String trafficSource) {
        this.trafficSource = trafficSource;
    }

    public Integer getIsAllowAliPay() {
        return isAllowAliPay;
    }

    public void setIsAllowAliPay(Integer isAllowAliPay) {
        this.isAllowAliPay = isAllowAliPay;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getPayFrom() {
		return payFrom;
	}

	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}	

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

    public Integer getCrossPay() {
        return crossPay;
    }

    public void setCrossPay(Integer crossPay) {
        this.crossPay = crossPay;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String toRequestString() {
		String str = "a=1";
		String[] a = this.toString().replace("PaymentOrder [","").replace("]","").replaceAll(" +","").split(",");
		for (int i = 0; i < a.length; i++) {			
			String[] b = a[i].split("=");
			if(b.length >1 && StringUtils.isNotBlank(b[1].trim()) && !b[1].equals("null")){
				str = str + "&" + b[0] + "=" + b[1];
			}
		}
		return str;
	}

}
