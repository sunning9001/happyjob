// 日期选择器
$(".datepicker").datepicker({
    language: "zh-CN",
    autoclose: true,//选中之后自动隐藏日期选择框
    format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
});
var list= [];
var totalPage=1;

var listParams = {
	    phoneNo:"",
	    resource:"",
	    startTime:"",
	    endTime:"",
	    blackOn:"",
	    userType:2,
	    currentPage:1,
	    showCount:15
}

//查询
$(document).on("click","#queryUser",function(){
	pageSearch(1);
})

 $(function(){  
     $(".idStyle").click(function(){  
         var _this = $(this);
         var src = _this.attr("src");
         $("#imgInModalID").attr("src", src);
         $('#imgModal').modal('toggle');
     });  
 }); 

//初始化年份
$(document).on("click","#bornTime2",function(){
	var option ;
	var date=new Date;
	var year=date.getFullYear();
	for(var i=1950;i<year;i++){
		option += '<option value="'+i+'" >'+i+'</option>'
	}
	if(option !=''){
		$("#bornTime2").append(option);
	}
})

function pageSearch(page){
	listParams.phoneNo = $("#phoneNo").val();
	listParams.resource = $("#registResource").val();
	listParams.startTime = dateToStartTime($("#startTime").val());
	listParams.endTime = dateToEndTime($("#endTime").val());
	listParams.blackOn = $("#blackOn").val();
	listParams.userType = $("#userType").val();
	listParams.currentPage = page;
	fetchList();
}



// 禁用
$(document).on("click",".forbidden",function(){
	var $row = $(this).parents("tr");
	var hpUserId = $row.data("user-id");
	var data ={};
	data.hpUserId = hpUserId ;
	
    swal({
        title: '是否禁用/启用该用户',
        text: '',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: '禁用',
        cancelButtonText: '启用',
        }).then(function(isConfirm) {
        if (isConfirm === true) {
        	data.blackOn = 1 ;
        	postAuth(data);
            swal(
            '禁用!',
            '禁用成功',
            'success'
            );			
        } else if (isConfirm === false) {
        	data.blackOn = 0 ;
        	postAuth(data);
        	swal(
            '启用',
            '启用成功',
            'success'
            );
        
        } else {
            // Esc, close button or outside click
            // isConfirm is undefined
        }
    }); 
})
// 认证
$(document).on("click",".auth",function(){
	var $row = $(this).parents("tr");
	var hpUserId = $row.data("user-id");
	var data ={};
	data.hpUserId = hpUserId ;
    swal({
        title: '认证',
        text: '是否通过认证!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: '通过',
        cancelButtonText: '不通过',
        }).then(function(isConfirm) {
        if (isConfirm === true) {
        	data.approve = 1 ;
        	postAuth(data);
            swal(
            '认证!',
            '认证成功',
            'success'
            );
        
        } else if (isConfirm === false) {
        	data.approve = 2 ;
        	postAuth(data);
            swal(
            '认证！',
            '认证不通过',
            'error'
            );
        
        } else {
            // Esc, close button or outside click
            // isConfirm is undefined
        }
    }); 
})
// 启用
$(document).on("click",".restart",function(){
    swal({
        title: '是否对该用户启用？',
        text: '',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: '是',
        cancelButtonText: '否',
        }).then(function(isConfirm) {
        if (isConfirm === true) {
            swal(
            'Deleted!',
            'Your imaginary file has been deleted.',
            'success'
            );
        
        } else if (isConfirm === false) {
            swal(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
            );
        
        } else {
            // Esc, close button or outside click
            // isConfirm is undefined
        }
    }); 
})
// 查看
$(document).on("click",".cat",function(){
    var $row = $(this).parents("tr");
    var iphone=$row.data("iphone");
    var name=$row.data("name");
    var bornYear=$row.data("born-year");
    var idNum=$row.data("id-num");
    var createTime=$row.data("create-time");
    var comName=$row.data("comname");
    var approveState=$row.data("approve-state");
    var idFrontPic=$row.data("id-front-pic");
    var idBackPic=$row.data("id-back-pic");
    var idPersonPic=$row.data("id-person-pic");
 
    var $obj = $("#browseModal").find(".showValue");
    $obj.eq(0).html(iphone)
    $obj.eq(1).html(name)
    $obj.eq(2).html(bornYear)
    $obj.eq(3).html(idNum)
    $obj.eq(4).html(createTime)
    $obj.eq(5).html(comName)
    $obj.eq(6).html(approveState)
    $obj.eq(7).attr("src",idFrontPic)
    $obj.eq(8).attr("src",idBackPic)
    $obj.eq(9).attr("src",idPersonPic)
    $('#browseModal').modal('toggle')
})

// 添加
$(document).on("click","#newUser",function(){
	var data ={} ;
	data.userType = $("#userType2").val();
	data.userName =$("#userName2").val();
	data.password1 =$("#password1").val();
	data.password2 =$("#password2").val();
	data.gender =$("#gender2").val();
	data.phoneNo =$("#phoneNo2").val();
	data.bornTime =$("#bornTime2").val();
	data.realName =$("#realName2").val();
	data.blackOn = $("input[type='radio']:checked").val();
	
	fetchPostBody({
		url:apiData.addUser,
		params:data,
		callback:function(data){
			$('#addUserModal').modal('hide');
			fetchList();
			swal('请求完成', data.message, 'success');
		}
	});
	
})

$('#addUserModal').on('hidden.bs.modal', function (){
	document.getElementById("contentForm").reset();
});


fetchList();
/**
 *
applyTime (integer, optional): 认证申请时间 ,
approveNum (integer, optional): 认证申请次数 ,
approveState (integer, optional): 认证状态（0、未申请认证，1、认证通过，2、认证不通过，3、认证待审核） ,
blackOn (integer, optional): 是否被禁用 ,
bornYear (integer, optional): 出生年份（时间戳s），计算年龄 ,
comName (string, optional): 公司名称 ,
createTime (integer, optional): 创建时间 ,
gender (integer, optional): 性别（1、男，2、女） ,
headerPic (string, optional): 头像 ,
hpCompanyId (integer, optional): 企业id ,
hpUserId (integer, optional): 用户ID ,
idBackPic (string, optional): 身份证反面图片 ,
idFrontPic (string, optional): 身份证正面图片 ,
idNum (string, optional): 身份证号 ,
idPersonPic (string, optional): 身份证手持图片 ,
inviteUserId (integer, optional): 邀请人ID ,
loginIp (string, optional): 登录ip ,
loginTime (integer, optional): 最近登录时间 ,
password (string, optional): 密码 ,
phoneNo (string, optional): 手机号码 ,
realName (string, optional): 真实姓名 ,
registResource (integer, optional): 注册来源（1、APP，2、小程序，3、微信，4、门店，5、邀请注册） ,
salt (string, optional): 密码加盐 ,
shareToken (string, optional): 分享身份识别码 ,
tokenTime (integer, optional): token有效时间 ,
userMoney (number, optional): 账户余额 ,
userName (string, optional): 用户名（数字、字母、下划线） ,
userToken (string, optional): 登录token ,
userType (integer, optional): 账号类型（1、超级管理员，2、求职者） ,
vipOn (integer, optional): 是否会员
 */

 /**
  * 
blackOn (integer, optional): 用户是否被禁用 ,
currentPage (integer, optional): 当前页 ,
currentResult (integer, optional): 当前记录起始索引 ,
endTime (integer, optional): 注册时间截止 ,
entityOrField (boolean, optional): true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性 ,
isPage (integer, optional): 是否分页:自定义分页时使用，插件分页不需要赋值 ,
phoneNo (string, optional): 用户手机号 ,
resource (integer, optional): 用户注册来源 ,
showCount (integer, optional): 每页显示记录数 ,
startTime (integer, optional): 注册时间开始 ,
totalPage (integer, optional): 总页数 ,
totalResult (integer, optional): 总记录数 ,
userType (integer, optional): 用户是否被禁用
  */

// 获取table数据
function fetchList(){
    fetchGet({
        url:"/backUser/userList",
        params:listParams,
        callback:function(data){
            list = data.list;
            listParams.currentPage = data.page.currentPage;
            totalPage= data.page.totalPage;
            addTableList(list);
            publicObj.pageShow(data.page,pageSearch);
        }
    })    
}
// 认证、禁用、启用请求
//hpUserId approve blackOn
function postAuth(data){
    fetchPost({
        url:"/backUser/userInfoUp",
        params: data,
        callback:function(data){
            console.log(data);
            fetchList();
        }
    })
}
// 添加table数据
function addTableList(list){
    var $table = $("#userList");
    var $tBody = $table.find("tbody");
    var templeteTr="";
    list.forEach(function(item){
        templeteTr+='\
        <tr \
            data-user-id="'+ item.hpUserId +'" \
            data-iphone="'+ item.phoneNo +'" \
            data-name="'+ getUserName(item.userName,item.realName) +'" \
            data-born-year="'+ bornYear(item.bornYear) +'" \
            data-id-num="'+ item.idNum +'" \
            data-create-time="'+ timestampToTime(item.createTime) +'" \
            data-comname="'+ (item.comName) +'" \
            data-approve-state="'+ approveState(item.approveState)  +'" \
            data-id-front-pic="'+ item.idFrontPic +'" \
            data-id-back-pic="'+ item.idBackPic +'" \
            data-id-person-pic="'+ item.idPersonPic +'" >\
            <th>'+ item.phoneNo +'</th>\
            <th>'+ getUserName(item.userName,item.realName) +'</th>\
            <th>'+ gender(item.gender) +'</th>\
            <th>'+ bornYear(item.bornYear) +'</th>\
            <th>'+ userType(item.userType) +'</th>\
            <th>'+ timestampToTime(item.createTime) +'</th>\
            <th>'+ registResource(item.registResource) +'</th>\
            <th>'+ isNull(item.storeName) +'</th>\
            <th>'+ approveState(item.approveState) +'</th>\
            <th>\
                <button type="button" class="btn btn-default btn-sm cat">查看</button>\
                <button type="button" class="btn btn-primary btn-sm auth">认证</button>\ ' ;
               
            if(item.blackOn ==0){
	        	templeteTr += '<button type="button" class="btn btn-danger btn-sm forbidden">禁用</button>' ;
	        }else{
	        	templeteTr += '<button type="button" class="btn btn-danger btn-sm forbidden ">已禁用</button>' ;
	        }
        
        		templeteTr  += '\
            </th>\
        </tr>';
                // <button type="button" class="btn btn-danger btn-sm restart">启用</button>\
    })
    $tBody.html(templeteTr)    
}
// 判断性别
function gender(gender){    
    return gender==1?"男":"女"
}
//判断账号类别
function userType(userType){
    return userType==1?"超级管理员":"求职者"
}
//判断出生年份
function bornYear(value){
	if(value){
		
		return  new Date().getFullYear()- Number(value)
	}else{
		return '无';
	}
}
//时间戳转date
function timestampToTime(timestamp) {
    var date = new Date(timestamp * 1000); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = change(date.getDate()) + ' ';
    var h = change(date.getHours()) + ':';
    var m = change(date.getMinutes()) + ':';
    var s = change(date.getSeconds());
    return Y + M + D + h + m + s;
}



function dateToStartTime(timestamp) {
	if(timestamp != null && timestamp !=""){
		var formatTimeS = new Date(timestamp+" 00:00:00").getTime();
		return formatTimeS/1000;
	}
	return 0;
}

function dateToEndTime(timestamp) {
	if(timestamp != null && timestamp !=""){
		var formatTimeS = new Date(timestamp+" 23:59:59").getTime();
		return formatTimeS/1000;
	}
	return 0;
}


function change(t) {
    if (t < 10) {
        return "0" + t;
    } else {
        return t;
    }
}
// 判断途径
function registResource(value){
    switch (value) {
        case 1:return "APP"
        case 2:return "小程序"
        case 3:return "微信"
        case 4:return "门店"
        case 5:return "邀请注册"
        default:return ""
    }
}
//判断是否认证
function approveState(value){
    switch (value) {
        case 0:return "未申请认证"
        case 1:return "认证通过"
        case 2:return "认证不通过"
        case 3:return "认证待审核"
    }
}
function getUserName(userName,realName){
	if(userName){
		return decodeURIComponent(userName);
	}else if(realName){
		return decodeURIComponent(realName);
	}else {
		return "无";
	}
}