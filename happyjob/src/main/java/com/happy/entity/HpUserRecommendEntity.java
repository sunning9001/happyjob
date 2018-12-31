package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 求职者推荐关系表
 */
@ApiModel(value="HpUserRecommend对象",description="求职者推荐关系表")
public class HpUserRecommendEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//求职者推荐关系表ID
	@ApiModelProperty(name="hpUserRecommendId",value="求职者推荐关系表ID",dataType="java.lang.Long")
	private java.lang.Long hpUserRecommendId;
	//员工微信绑定表ID
	@ApiModelProperty(name="hpUserBoundId",value="员工微信绑定表ID",dataType="java.lang.Long")
	private java.lang.Long hpUserBoundId;
	//被推荐者手机号
	@ApiModelProperty(name="recPhoneNo",value="被推荐者手机号",dataType="String")
	private String recPhoneNo;
	//记录时间
	@ApiModelProperty(name="recTime",value="记录时间",dataType="java.lang.Long")
	private java.lang.Long recTime;

	public java.lang.Long getHpUserRecommendId() {
		return hpUserRecommendId;
	}


	public void setHpUserRecommendId(java.lang.Long hpUserRecommendId) {
		this.hpUserRecommendId = hpUserRecommendId;
	}


	public java.lang.Long getHpUserBoundId() {
		return hpUserBoundId;
	}


	public void setHpUserBoundId(java.lang.Long hpUserBoundId) {
		this.hpUserBoundId = hpUserBoundId;
	}


	public String getRecPhoneNo() {
		return recPhoneNo;
	}


	public void setRecPhoneNo(String recPhoneNo) {
		this.recPhoneNo = recPhoneNo;
	}


	public java.lang.Long getRecTime() {
		return recTime;
	}


	public void setRecTime(java.lang.Long recTime) {
		this.recTime = recTime;
	}

}