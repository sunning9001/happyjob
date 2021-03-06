package com.happy.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 
 */
@ApiModel(value="HpUser对象",description="")
public class HpUserEntity implements  Serializable  {
	private static final long serialVersionUID = 1L;

	//用户ID
	@ApiModelProperty(name="hpUserId",value="用户ID")
	private java.lang.Long hpUserId;
	//企业id
	@ApiModelProperty(name="hpCompanyId",value="企业id")
	private java.lang.Long hpCompanyId;
	//账号类型（1、超级管理员，2、求职者）
	@ApiModelProperty(name="userType",value="账号类型（1、超级管理员，2、求职者）")
	private java.lang.Integer userType;
	//用户名（数字、字母、下划线）
	@ApiModelProperty(name="userName",value="用户名（数字、字母、下划线）")
	private String userName;
	//密码
	@ApiModelProperty(name="password",value="密码")
	private String password;
	//密码加盐
	@ApiModelProperty(name="salt",value="密码加盐")
	private String salt;
	//性别（1、男，2、女）
	@ApiModelProperty(name="gender",value="性别（1、男，2、女）")
	private java.lang.Integer gender;
	//手机号码
	@ApiModelProperty(name="phoneNo",value="手机号码")
	private String phoneNo;
	//出生年份（时间戳s），计算年龄
	@ApiModelProperty(name="bornYear",value="出生年份（时间戳s），计算年龄")
	private java.lang.Long bornYear;
	//真实姓名
	@ApiModelProperty(name="realName",value="真实姓名")
	private String realName;
	//是否被禁用
	@ApiModelProperty(name="blackOn",value="是否被禁用")
	private java.lang.Integer blackOn;
	//认证状态（0、未申请认证，1、认证通过，2、认证不通过，3、认证待审核）
	@ApiModelProperty(name="approveState",value="认证状态（0、未申请认证，1、认证通过，2、认证不通过，3、认证待审核）")
	private java.lang.Integer approveState;
	//头像
	@ApiModelProperty(name="headerPic",value="头像")
	private String headerPic;
	//身份证号
	@ApiModelProperty(name="idNum",value="身份证号")
	private String idNum;
	//身份证正面图片
	@ApiModelProperty(name="idFrontPic",value="身份证正面图片")
	private String idFrontPic;
	//身份证反面图片
	@ApiModelProperty(name="idBackPic",value="身份证反面图片")
	private String idBackPic;
	//身份证手持图片
	@ApiModelProperty(name="idPersonPic",value="身份证手持图片")
	private String idPersonPic;
	//创建时间
	@ApiModelProperty(name="createTime",value="创建时间")
	private java.lang.Long createTime;
	//注册来源（1、APP，2、小程序，3、微信，4、门店，5、邀请注册）
	@ApiModelProperty(name="registResource",value="注册来源（1、APP，2、小程序，3、微信，4、门店，5、邀请注册）")
	private java.lang.Integer registResource;
	//邀请人ID
	@ApiModelProperty(name="inviteUserId",value="邀请人ID")
	private java.lang.Long inviteUserId;
	//账户余额
	@ApiModelProperty(name="userMoney",value="账户余额")
	private Double userMoney;
	//认证申请次数
	@ApiModelProperty(name="approveNum",value="认证申请次数")
	private java.lang.Integer approveNum;
	//认证申请时间
	@ApiModelProperty(name="applyTime",value="认证申请时间")
	private java.lang.Long applyTime;
	//是否会员
	@ApiModelProperty(name="vipOn",value="是否会员")
	private java.lang.Integer vipOn;
	//登录token
	@ApiModelProperty(name="userToken",value="登录token")
	private String userToken;
	//token有效时间
	@ApiModelProperty(name="tokenTime",value="token有效时间")
	private java.lang.Long tokenTime;
	//最近登录时间
	@ApiModelProperty(name="loginTime",value="最近登录时间")
	private java.lang.Long loginTime;
	//登录ip
	@ApiModelProperty(name="loginIp",value="登录ip")
	private String loginIp;
	//分享身份识别码
	@ApiModelProperty(name="shareToken",value="分享身份识别码")
	private String shareToken;
	//公司门店表ID（代表扫码注册门店来源）
	@ApiModelProperty(name="hpCompanyStoreId",value="公司门店表ID（代表扫码注册门店来源）")
	private java.lang.Long hpCompanyStoreId;

	public java.lang.Long getHpUserId() {
		return hpUserId;
	}


	public void setHpUserId(java.lang.Long hpUserId) {
		this.hpUserId = hpUserId;
	}


	public java.lang.Long getHpCompanyId() {
		return hpCompanyId;
	}


	public void setHpCompanyId(java.lang.Long hpCompanyId) {
		this.hpCompanyId = hpCompanyId;
	}


	public java.lang.Integer getUserType() {
		return userType;
	}


	public void setUserType(java.lang.Integer userType) {
		this.userType = userType;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}


	public java.lang.Integer getGender() {
		return gender;
	}


	public void setGender(java.lang.Integer gender) {
		this.gender = gender;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public java.lang.Long getBornYear() {
		return bornYear;
	}


	public void setBornYear(java.lang.Long bornYear) {
		this.bornYear = bornYear;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public java.lang.Integer getBlackOn() {
		return blackOn;
	}


	public void setBlackOn(java.lang.Integer blackOn) {
		this.blackOn = blackOn;
	}


	public java.lang.Integer getApproveState() {
		return approveState;
	}


	public void setApproveState(java.lang.Integer approveState) {
		this.approveState = approveState;
	}


	public String getHeaderPic() {
		return headerPic;
	}


	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}


	public String getIdNum() {
		return idNum;
	}


	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}


	public String getIdFrontPic() {
		return idFrontPic;
	}


	public void setIdFrontPic(String idFrontPic) {
		this.idFrontPic = idFrontPic;
	}


	public String getIdBackPic() {
		return idBackPic;
	}


	public void setIdBackPic(String idBackPic) {
		this.idBackPic = idBackPic;
	}


	public String getIdPersonPic() {
		return idPersonPic;
	}


	public void setIdPersonPic(String idPersonPic) {
		this.idPersonPic = idPersonPic;
	}


	public java.lang.Long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}


	public java.lang.Integer getRegistResource() {
		return registResource;
	}


	public void setRegistResource(java.lang.Integer registResource) {
		this.registResource = registResource;
	}


	public java.lang.Long getInviteUserId() {
		return inviteUserId;
	}


	public void setInviteUserId(java.lang.Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}


	public Double getUserMoney() {
		return userMoney;
	}


	public void setUserMoney(Double userMoney) {
		this.userMoney = userMoney;
	}


	public java.lang.Integer getApproveNum() {
		return approveNum;
	}


	public void setApproveNum(java.lang.Integer approveNum) {
		this.approveNum = approveNum;
	}


	public java.lang.Long getApplyTime() {
		return applyTime;
	}


	public void setApplyTime(java.lang.Long applyTime) {
		this.applyTime = applyTime;
	}


	public java.lang.Integer getVipOn() {
		return vipOn;
	}


	public void setVipOn(java.lang.Integer vipOn) {
		this.vipOn = vipOn;
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}


	public java.lang.Long getTokenTime() {
		return tokenTime;
	}


	public void setTokenTime(java.lang.Long tokenTime) {
		this.tokenTime = tokenTime;
	}


	public java.lang.Long getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(java.lang.Long loginTime) {
		this.loginTime = loginTime;
	}


	public String getLoginIp() {
		return loginIp;
	}


	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


	public String getShareToken() {
		return shareToken;
	}


	public void setShareToken(String shareToken) {
		this.shareToken = shareToken;
	}


	public java.lang.Long getHpCompanyStoreId() {
		return hpCompanyStoreId;
	}


	public void setHpCompanyStoreId(java.lang.Long hpCompanyStoreId) {
		this.hpCompanyStoreId = hpCompanyStoreId;
	}

}