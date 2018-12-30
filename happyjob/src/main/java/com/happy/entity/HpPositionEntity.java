package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 公司招聘岗位表
 */
@ApiModel(value="HpPosition对象",description="公司招聘岗位表")
public class HpPositionEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//公司招聘岗位表id
	@ApiModelProperty(name="hpPositionId",value="公司招聘岗位表id",dataType="java.lang.Long")
	private java.lang.Long hpPositionId;
	//企业id
	@ApiModelProperty(name="hpCompanyId",value="企业id",dataType="java.lang.Long")
	private java.lang.Long hpCompanyId;
	//公司职位招聘形式表
	@ApiModelProperty(name="hpPositionOfferId",value="公司职位招聘形式表",dataType="java.lang.Long")
	private java.lang.Long hpPositionOfferId;
	//公司职位招聘要求表
	@ApiModelProperty(name="hpPositionRequireId",value="公司职位招聘要求表",dataType="java.lang.Long")
	private java.lang.Long hpPositionRequireId;
	//区ID（工作地点）
	@ApiModelProperty(name="countyId",value="区ID（工作地点）",dataType="java.lang.Long")
	private java.lang.Long countyId;
	//公司招聘岗位月薪表ID
	@ApiModelProperty(name="hpPositionSalaryId",value="公司招聘岗位月薪表ID",dataType="java.lang.Long")
	private java.lang.Long hpPositionSalaryId;
	//学历选项表ID（-1、学历不限，0、其他）
	@ApiModelProperty(name="hpEducationId",value="学历选项表ID（-1、学历不限，0、其他）",dataType="java.lang.Long")
	private java.lang.Long hpEducationId;
	//职位名称
	@ApiModelProperty(name="posName",value="职位名称",dataType="String")
	private String posName;
	//入职返现金额男
	@ApiModelProperty(name="reManMoney",value="入职返现金额男",dataType="Double")
	private Double reManMoney;
	//多少天后入职返现男
	@ApiModelProperty(name="manDayNum",value="多少天后入职返现男",dataType="java.lang.Integer")
	private java.lang.Integer manDayNum;
	//入职返现金额女
	@ApiModelProperty(name="reWomanMoney",value="入职返现金额女",dataType="java.lang.Integer")
	private java.lang.Integer reWomanMoney;
	//多少天后入职返现女
	@ApiModelProperty(name="womenDayNum",value="多少天后入职返现女",dataType="java.lang.Integer")
	private java.lang.Integer womenDayNum;
	//是否开启高薪
	@ApiModelProperty(name="urgentOn",value="是否开启高薪",dataType="java.lang.Integer")
	private java.lang.Integer urgentOn;
	//高薪急聘平台补贴金额
	@ApiModelProperty(name="urgentMoney",value="高薪急聘平台补贴金额",dataType="java.lang.Integer")
	private java.lang.Integer urgentMoney;
	//是否是拼团岗位
	@ApiModelProperty(name="groupOn",value="是否是拼团岗位",dataType="java.lang.Integer")
	private java.lang.Integer groupOn;
	//三人团奖励金额
	@ApiModelProperty(name="threeMoney",value="三人团奖励金额",dataType="java.lang.Integer")
	private java.lang.Integer threeMoney;
	//无人团及以上奖励金额
	@ApiModelProperty(name="fiveMoney",value="无人团及以上奖励金额",dataType="java.lang.Integer")
	private java.lang.Integer fiveMoney;
	//是否是福利岗位
	@ApiModelProperty(name="isWelfare",value="是否是福利岗位",dataType="java.lang.Integer")
	private java.lang.Integer isWelfare;
	//福利岗位详情
	@ApiModelProperty(name="welfareDetail",value="福利岗位详情",dataType="String")
	private String welfareDetail;
	//工作时限
	@ApiModelProperty(name="jobHours",value="工作时限",dataType="String")
	private String jobHours;
	//公司客服
	@ApiModelProperty(name="comCustPhone",value="公司客服",dataType="String")
	private String comCustPhone;
	//职位行业类型
	@ApiModelProperty(name="posType",value="职位行业类型",dataType="String")
	private String posType;
	//基本信息
	@ApiModelProperty(name="posDetail",value="基本信息",dataType="String")
	private String posDetail;
	//是否有班车
	@ApiModelProperty(name="carOn",value="是否有班车",dataType="java.lang.Integer")
	private java.lang.Integer carOn;
	//班车信息
	@ApiModelProperty(name="carDesc",value="班车信息",dataType="String")
	private String carDesc;
	//公司介绍
	@ApiModelProperty(name="posComDesc",value="公司介绍",dataType="String")
	private String posComDesc;
	//其他福利
	@ApiModelProperty(name="otherWelfare",value="其他福利",dataType="String")
	private String otherWelfare;
	//职位性质（1、实习，2、兼职，3、全职）
	@ApiModelProperty(name="posNature",value="职位性质（1、实习，2、兼职，3、全职）",dataType="java.lang.Integer")
	private java.lang.Integer posNature;
	//工作经验
	@ApiModelProperty(name="posWorkYear",value="工作经验",dataType="String")
	private String posWorkYear;
	//开始时间
	@ApiModelProperty(name="startTime",value="开始时间",dataType="java.lang.Long")
	private java.lang.Long startTime;
	//结束时间
	@ApiModelProperty(name="endTime",value="结束时间",dataType="java.lang.Long")
	private java.lang.Long endTime;
	//招聘人数（0,、表示若干）
	@ApiModelProperty(name="posNum",value="招聘人数（0,、表示若干）",dataType="java.lang.Integer")
	private java.lang.Integer posNum;
	//联系人
	@ApiModelProperty(name="posPerson",value="联系人",dataType="String")
	private String posPerson;
	//联系电话
	@ApiModelProperty(name="posPhone",value="联系电话",dataType="String")
	private String posPhone;
	//接收邮箱
	@ApiModelProperty(name="posEmail",value="接收邮箱",dataType="String")
	private String posEmail;
	//是否热门
	@ApiModelProperty(name="hotOn",value="是否热门",dataType="java.lang.Integer")
	private java.lang.Integer hotOn;

	public java.lang.Long getHpPositionId() {
		return hpPositionId;
	}


	public void setHpPositionId(java.lang.Long hpPositionId) {
		this.hpPositionId = hpPositionId;
	}


	public java.lang.Long getHpCompanyId() {
		return hpCompanyId;
	}


	public void setHpCompanyId(java.lang.Long hpCompanyId) {
		this.hpCompanyId = hpCompanyId;
	}


	public java.lang.Long getHpPositionOfferId() {
		return hpPositionOfferId;
	}


	public void setHpPositionOfferId(java.lang.Long hpPositionOfferId) {
		this.hpPositionOfferId = hpPositionOfferId;
	}


	public java.lang.Long getHpPositionRequireId() {
		return hpPositionRequireId;
	}


	public void setHpPositionRequireId(java.lang.Long hpPositionRequireId) {
		this.hpPositionRequireId = hpPositionRequireId;
	}


	public java.lang.Long getCountyId() {
		return countyId;
	}


	public void setCountyId(java.lang.Long countyId) {
		this.countyId = countyId;
	}


	public java.lang.Long getHpPositionSalaryId() {
		return hpPositionSalaryId;
	}


	public void setHpPositionSalaryId(java.lang.Long hpPositionSalaryId) {
		this.hpPositionSalaryId = hpPositionSalaryId;
	}


	public java.lang.Long getHpEducationId() {
		return hpEducationId;
	}


	public void setHpEducationId(java.lang.Long hpEducationId) {
		this.hpEducationId = hpEducationId;
	}


	public String getPosName() {
		return posName;
	}


	public void setPosName(String posName) {
		this.posName = posName;
	}


	public Double getReManMoney() {
		return reManMoney;
	}


	public void setReManMoney(Double reManMoney) {
		this.reManMoney = reManMoney;
	}


	public java.lang.Integer getManDayNum() {
		return manDayNum;
	}


	public void setManDayNum(java.lang.Integer manDayNum) {
		this.manDayNum = manDayNum;
	}


	public java.lang.Integer getReWomanMoney() {
		return reWomanMoney;
	}


	public void setReWomanMoney(java.lang.Integer reWomanMoney) {
		this.reWomanMoney = reWomanMoney;
	}


	public java.lang.Integer getWomenDayNum() {
		return womenDayNum;
	}


	public void setWomenDayNum(java.lang.Integer womenDayNum) {
		this.womenDayNum = womenDayNum;
	}


	public java.lang.Integer getUrgentOn() {
		return urgentOn;
	}


	public void setUrgentOn(java.lang.Integer urgentOn) {
		this.urgentOn = urgentOn;
	}


	public java.lang.Integer getUrgentMoney() {
		return urgentMoney;
	}


	public void setUrgentMoney(java.lang.Integer urgentMoney) {
		this.urgentMoney = urgentMoney;
	}


	public java.lang.Integer getGroupOn() {
		return groupOn;
	}


	public void setGroupOn(java.lang.Integer groupOn) {
		this.groupOn = groupOn;
	}


	public java.lang.Integer getThreeMoney() {
		return threeMoney;
	}


	public void setThreeMoney(java.lang.Integer threeMoney) {
		this.threeMoney = threeMoney;
	}


	public java.lang.Integer getFiveMoney() {
		return fiveMoney;
	}


	public void setFiveMoney(java.lang.Integer fiveMoney) {
		this.fiveMoney = fiveMoney;
	}


	public java.lang.Integer getIsWelfare() {
		return isWelfare;
	}


	public void setIsWelfare(java.lang.Integer isWelfare) {
		this.isWelfare = isWelfare;
	}


	public String getWelfareDetail() {
		return welfareDetail;
	}


	public void setWelfareDetail(String welfareDetail) {
		this.welfareDetail = welfareDetail;
	}


	public String getJobHours() {
		return jobHours;
	}


	public void setJobHours(String jobHours) {
		this.jobHours = jobHours;
	}


	public String getComCustPhone() {
		return comCustPhone;
	}


	public void setComCustPhone(String comCustPhone) {
		this.comCustPhone = comCustPhone;
	}


	public String getPosType() {
		return posType;
	}


	public void setPosType(String posType) {
		this.posType = posType;
	}


	public String getPosDetail() {
		return posDetail;
	}


	public void setPosDetail(String posDetail) {
		this.posDetail = posDetail;
	}


	public java.lang.Integer getCarOn() {
		return carOn;
	}


	public void setCarOn(java.lang.Integer carOn) {
		this.carOn = carOn;
	}


	public String getCarDesc() {
		return carDesc;
	}


	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}


	public String getPosComDesc() {
		return posComDesc;
	}


	public void setPosComDesc(String posComDesc) {
		this.posComDesc = posComDesc;
	}


	public String getOtherWelfare() {
		return otherWelfare;
	}


	public void setOtherWelfare(String otherWelfare) {
		this.otherWelfare = otherWelfare;
	}


	public java.lang.Integer getPosNature() {
		return posNature;
	}


	public void setPosNature(java.lang.Integer posNature) {
		this.posNature = posNature;
	}


	public String getPosWorkYear() {
		return posWorkYear;
	}


	public void setPosWorkYear(String posWorkYear) {
		this.posWorkYear = posWorkYear;
	}


	public java.lang.Long getStartTime() {
		return startTime;
	}


	public void setStartTime(java.lang.Long startTime) {
		this.startTime = startTime;
	}


	public java.lang.Long getEndTime() {
		return endTime;
	}


	public void setEndTime(java.lang.Long endTime) {
		this.endTime = endTime;
	}


	public java.lang.Integer getPosNum() {
		return posNum;
	}


	public void setPosNum(java.lang.Integer posNum) {
		this.posNum = posNum;
	}


	public String getPosPerson() {
		return posPerson;
	}


	public void setPosPerson(String posPerson) {
		this.posPerson = posPerson;
	}


	public String getPosPhone() {
		return posPhone;
	}


	public void setPosPhone(String posPhone) {
		this.posPhone = posPhone;
	}


	public String getPosEmail() {
		return posEmail;
	}


	public void setPosEmail(String posEmail) {
		this.posEmail = posEmail;
	}


	public java.lang.Integer getHotOn() {
		return hotOn;
	}


	public void setHotOn(java.lang.Integer hotOn) {
		this.hotOn = hotOn;
	}

}