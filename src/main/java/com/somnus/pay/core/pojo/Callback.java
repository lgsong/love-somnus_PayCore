package com.somnus.pay.core.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.somnus.pay.core.enums.CallbackStatus;
import com.somnus.pay.core.enums.PayChannel;
import com.somnus.pay.core.thirdpay.RequestType;

@Entity
@Table(name = "t_payment_callback")
public class Callback implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private CallbackId id;
	
	@Column(name = "data")
	private String data;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CallbackStatus status;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@Column(name = "memo")
	private String memo;

	public Callback(){}
	
	public Callback(CallbackId id){
		this.id = id;
	}
	
	public Callback(CallbackId id, String data){
		this.id = id;
		this.data = data;
		this.status = CallbackStatus.INIT;
		this.createTime = new Date();
	}
	
	public CallbackId getId() {
		return id;
	}

	public void setId(CallbackId id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CallbackStatus getStatus() {
		return status;
	}

	public void setStatus(CallbackStatus status) {
		this.status = status;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String desc) {
		this.memo = desc;
	}
	
	@Embeddable
	public static class CallbackId implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Column(name = "order_id")
		private String orderId;
		
		@Column(name = "channel")
		@Enumerated(EnumType.STRING)
		private PayChannel channel;
		
		@Column(name = "type")
		@Enumerated(EnumType.STRING)
		private RequestType type;
		
		public CallbackId(){}
		
		public CallbackId(String orderId, PayChannel channel, RequestType type){
			this.orderId = orderId;
			this.channel = channel;
			this.type = type;
		}
		
		public String getOrderId() {
			return orderId;
		}
		
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		
		public PayChannel getChannel() {
			return channel;
		}
		
		public void setChannel(PayChannel channel) {
			this.channel = channel;
		}
		
		public RequestType getType() {
			return type;
		}
		
		public void setType(RequestType type) {
			this.type = type;
		}

		@Override
		public int hashCode() {
			return this.orderId.hashCode() + this.channel.hashCode() + this.type.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof CallbackId){  
				CallbackId id = (CallbackId) obj;
				return this.channel == id.getChannel() && this.type == id.getType() && this.orderId.equals(id.getOrderId());
			}
	        return false ;
		}
		
		public String toString(){
			return "orderId=" + this.orderId + ", channel=" + this.channel + ", type=" + this.type;
		}
		
	}
}

