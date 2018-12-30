package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 公司职位招聘参与表
 */
@ApiModel(value="HpPositionRefUser对象",description="公司职位招聘参与表")
public class HpPositionRefUserEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//公司职位招聘拼团参与表ID
	@ApiModelProperty(name="hpPositionRefUserId",value="公司职位招聘拼团参与表ID",dataType="java.lang.Long")
	private java.lang.Long hpPositionRefUserId;
	//公司职位招聘拼团表ID
	@ApiModelProperty(name="hpPositionGroupId",value="公司职位招聘拼团表ID",dataType="java.lang.Long")
	private java.lang.Long hpPositionGroupId;
	//公司招聘岗位表id
	@ApiModelProperty(name="hpPositionId",value="公司招聘岗位表id",dataType="java.lang.Long")
	private java.lang.Long hpPositionId;
	//是否是发起人
	@ApiModelProperty(name="leaderOn",value="是否是发起人",dataType="java.lang.Integer")
	private java.lang.Integer leaderOn;
	//申请时间
	@ApiModelProperty(name="partTime",value="申请时间",dataType="java.lang.Long")
	private java.lang.Long partTime;
	//申请类型（1、普通申请，2、拼团申请）
	@ApiModelProperty(name="partType",value="申请类型（1、普通申请，2、拼团申请）",dataType="java.lang.Integer")
	private java.lang.Integer partType;

	public java.lang.Long getHpPositionRefUserId() {
		return hpPositionRefUserId;
	}


	public void setHpPositionRefUserId(java.lang.Long hpPositionRefUserId) {
		this.hpPositionRefUserId = hpPositionRefUserId;
	}


	public java.lang.Long getHpPositionGroupId() {
		return hpPositionGroupId;
	}


	public void setHpPositionGroupId(java.lang.Long hpPositionGroupId) {
		this.hpPositionGroupId = hpPositionGroupId;
	}


	public java.lang.Long getHpPositionId() {
		return hpPositionId;
	}


	public void setHpPositionId(java.lang.Long hpPositionId) {
		this.hpPositionId = hpPositionId;
	}


	public java.lang.Integer getLeaderOn() {
		return leaderOn;
	}


	public void setLeaderOn(java.lang.Integer leaderOn) {
		this.leaderOn = leaderOn;
	}


	public java.lang.Long getPartTime() {
		return partTime;
	}


	public void setPartTime(java.lang.Long partTime) {
		this.partTime = partTime;
	}


	public java.lang.Integer getPartType() {
		return partType;
	}


	public void setPartType(java.lang.Integer partType) {
		this.partType = partType;
	}

}