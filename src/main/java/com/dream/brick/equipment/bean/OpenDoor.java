package com.dream.brick.equipment.bean;

import javax.persistence.*;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: OpenDoor.java
 * @Description: 开关门日志
 * @date 2018-03-27 下午5:04
 */
@Entity
@Table(name = "t_open_door")
public class OpenDoor {
	private String id;
	private String createTime;
    private String deviceName;
    private String deviceNum;
	//private char status;// 0开门，1关门
	private String openTime;

	private String context;
	private String messageType;

	private String rtuId;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/*public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}*/

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRtuId() {
		return rtuId;
	}

	public void setRtuId(String rtuId) {
		this.rtuId = rtuId;
	}
}
