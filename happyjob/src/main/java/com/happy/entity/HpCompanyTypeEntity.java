package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 
 */
@ApiModel(value="HpCompanyType对象",description="")
public class HpCompanyTypeEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//公司行业类型表id
	@ApiModelProperty(name="hpCompanyTypeId",value="公司行业类型表id")
	private java.lang.Long hpCompanyTypeId;
	//公司类型名称
	@ApiModelProperty(name="typeName",value="公司类型名称")
	private String typeName;
	//是否启用
	@ApiModelProperty(name="useOn",value="是否启用")
	private java.lang.Integer useOn;

	public java.lang.Long getHpCompanyTypeId() {
		return hpCompanyTypeId;
	}


	public void setHpCompanyTypeId(java.lang.Long hpCompanyTypeId) {
		this.hpCompanyTypeId = hpCompanyTypeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public java.lang.Integer getUseOn() {
		return useOn;
	}


	public void setUseOn(java.lang.Integer useOn) {
		this.useOn = useOn;
	}

}