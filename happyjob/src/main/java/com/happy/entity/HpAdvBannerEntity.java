package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 广告位轮播图表
 */
@ApiModel(value="HpAdvBanner对象",description="广告位轮播图表")
public class HpAdvBannerEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//广告位轮播图表ID
	@ApiModelProperty(name="hpAdvBannerId",value="广告位轮播图表ID",dataType="java.lang.Long")
	private java.lang.Long hpAdvBannerId;
	//标题
	@ApiModelProperty(name="title",value="标题",dataType="String")
	private String title;
	//创建时间
	@ApiModelProperty(name="createTime",value="创建时间",dataType="java.lang.Long")
	private java.lang.Long createTime;
	//结束时间
	@ApiModelProperty(name="endTime",value="结束时间",dataType="java.lang.Long")
	private java.lang.Long endTime;
	//排序
	@ApiModelProperty(name="sortNum",value="排序",dataType="java.lang.Long")
	private java.lang.Long sortNum;
	//图片地址
	@ApiModelProperty(name="picUrl",value="图片地址",dataType="String")
	private String picUrl;
	//图片详情链接
	@ApiModelProperty(name="targetUrl",value="图片详情链接",dataType="String")
	private String targetUrl;
	//是否开启
	@ApiModelProperty(name="useOn",value="是否开启",dataType="java.lang.Integer")
	private java.lang.Integer useOn;
	//是否删除
	@ApiModelProperty(name="delOn",value="是否删除",dataType="java.lang.Integer")
	private java.lang.Integer delOn;

	public java.lang.Long getHpAdvBannerId() {
		return hpAdvBannerId;
	}


	public void setHpAdvBannerId(java.lang.Long hpAdvBannerId) {
		this.hpAdvBannerId = hpAdvBannerId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public java.lang.Long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}


	public java.lang.Long getEndTime() {
		return endTime;
	}


	public void setEndTime(java.lang.Long endTime) {
		this.endTime = endTime;
	}


	public java.lang.Long getSortNum() {
		return sortNum;
	}


	public void setSortNum(java.lang.Long sortNum) {
		this.sortNum = sortNum;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getTargetUrl() {
		return targetUrl;
	}


	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}


	public java.lang.Integer getUseOn() {
		return useOn;
	}


	public void setUseOn(java.lang.Integer useOn) {
		this.useOn = useOn;
	}


	public java.lang.Integer getDelOn() {
		return delOn;
	}


	public void setDelOn(java.lang.Integer delOn) {
		this.delOn = delOn;
	}

}