// 日期选择器
$(".datepicker").datepicker({
    language: "zh-CN",
    autoclose: true,//选中之后自动隐藏日期选择框
    format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
});

$('#upPicLogo').on('change',function(){                                                 //选中图片后展示在页面
	   var filePath = $(this)[0].files[0].name //获取到input的value，里面是文件的路径 
	   console.log(filePath) //1.png 
	   fileFormat = filePath.split('.')[1].toLowerCase() 
	   console.log(fileFormat) //png
	   src = window.URL.createObjectURL(this.files[0]) //转成可以在本地预览的格式 
	   console.log(src) //blob:null/11ea5a2d-7270-4035-b5c4-4e50c5c061e7

	   // 检查是否是图片 
	   if( !fileFormat.match(/png|jpg|jpeg/) ) { 
	      alert('上传错误,文件格式必须为：png/jpg/jpeg')
	     return 
	  	} 

	    $('#imgContent').attr('src',src);
	    
	    var url = window.location.origin + "/wxAppletsLogin/imgUpOne" ;
		var file = $("#upPicLogo").get(0).files[0];
		if(!file){
			return;
		}
		var formData = new FormData();
		formData.append("file",file);
		formData.append("code","company");
		$.ajax({
			url:url,
			dataType:"json",
			type:"post",
			data:formData,
	        processData: false,  // 不处理数据
	        contentType: false,   // 不设置内容类型
			header:{
				oid:"fad7bd3d01f04950b1d906584afc9253",
			},
			success:function(data){
				console.log("=data.data.imgUrl==",data.data.imgUrl);
				comLogo = data.data.imgUrl ;
			}
		});

})

$('#upPicLis').on('change',function(){                                                 //选中图片后展示在页面
	   var filePath = $(this)[0].files[0].name //获取到input的value，里面是文件的路径 
	   console.log(filePath) //1.png 
	   fileFormat = filePath.split('.')[1].toLowerCase() 
	   console.log(fileFormat) //png
	   src = window.URL.createObjectURL(this.files[0]) //转成可以在本地预览的格式 
	   console.log(src) //blob:null/11ea5a2d-7270-4035-b5c4-4e50c5c061e7

	   // 检查是否是图片 
	   if( !fileFormat.match(/png|jpg|jpeg/) ) { 
	      alert('上传错误,文件格式必须为：png/jpg/jpeg')
	     return 
	  	} 

	    $('#imgContent2').attr('src',src)
	    
		var url = window.location.origin + "/wxAppletsLogin/imgUpOne" ;
		var file = $("#upPicLis").get(0).files[0];
		if(!file){
			return;
		}
		var formData = new FormData();
		formData.append("file",file);
		formData.append("code","company");
		$.ajax({
			url:url,
			dataType:"json",
			type:"post",
			data:formData,
	        processData: false,  // 不处理数据
	        contentType: false,   // 不设置内容类型
			header:{
				oid:"fad7bd3d01f04950b1d906584afc9253",
			},
			success:function(data){
				console.log("==data.data.imgUrl=",data.data.imgUrl);
				comLicense = data.data.imgUrl ;
			}
		});
		

})
	
// 查询
$(document).on("click",".queryCompany",function(){
	pageSearch(1);
})
//var width ;
//var height ;
// $(function(){  
//        $('.idStyle').mouseenter(function(){  
//        	  var wValue=3 * $(this).width();  
//	          var hValue=3 * $(this).height();        
//	            $(this).stop().animate({width: wValue,  
//	                            height: hValue,  
//	                            left:"0px",  
//                                top:"0px"}, 1000);  
//	     }).mouseleave(function(){  
//	            $(this).stop().animate({width: "100",  
//	                                         height: "100",  
//	                                         left:"0px",  
//	                                         top:"0px"}, 1000 );  
//	        });  
//
//
//}); 
 
 $(function(){  
     $(".idStyle").click(function(){  
         var _this = $(this);//将当前的pimg元素作为_this传入函数  
         var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
         $("#imgInModalID").attr("src", src);//设置#bigimg元素的src属性
         
         $('#imgModal').modal('toggle');
     });  
 });  

 function imgShow(outerdiv, innerdiv, bigimg, _this){  
     var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
     $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
   
         /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
     $("<img/>").attr("src", src).load(function(){  
         var windowW = $(window).width();//获取当前窗口宽度  
         var windowH = $(window).height();//获取当前窗口高度  
         var realWidth = this.width;//获取图片真实宽度  
         var realHeight = this.height;//获取图片真实高度  
         var imgWidth, imgHeight;  
         var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
           
         if(realHeight>windowH*scale) {//判断图片高度  
             imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
             imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
             if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                 imgWidth = windowW*scale;//再对宽度进行缩放  
             }  
         } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
             imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                         imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
         } else {//如果图片真实高度和宽度都符合要求，高宽不变  
             imgWidth = realWidth;  
             imgHeight = realHeight;  
         }  
                 $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
           
         var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
         var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
         $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
         $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
     });  
       
     $(outerdiv).click(function(){//再次点击淡出消失弹出层  
         $(this).fadeOut("fast");  
     });  
 }  
 
//分页查询
function pageSearch(page){
	listParams.comName = $("#comNameSearch").val();
	listParams.startTime = dateToStartTime($("#startTimeSearch").val());
	listParams.endTime = dateToEndTime($("#endTimeSearch").val());
	listParams.currentPage = page;
	fetchList();
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

$('#addCompanyModel').on('hidden.bs.modal', function (){
	document.getElementById("contentForm").reset();
	 $('#imgContent').attr('src',"");
	 $('#imgContent2').attr('src',"");
});

// 认证
$(document).on("click",".auth",function(){
	var $row = $(this).parents("tr");
	var companyId = $row.data("company-id");
	var data ={};
	data.companyId =companyId ;
	
    swal({
        title: '认证',
        text: '是否通过认证!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: '通过',
        cancelButtonText: '不通过',
        }).then(function(isConfirm) {
        if (isConfirm === true) {
        	data.approveState = 1 ;
        	postAuth(data);
        	
            swal(
            '认证',
            '认证通过',
            'success'
            );
        
        } else if (isConfirm === false) {
        	data.approveState = 2 ;
        	postAuth(data);
            swal(
            		 '认证',
                     '认证不通过',
                     'error'
            );
        } 
    }); 
})

// 删除
$(document).on("click",".del",function(){
	var $row = $(this).parents("tr");
	var companyId = $row.data("company-id");
	var data ={};
	data.companyId =companyId ;
    swal({
    	  title: '删除',
          text: '请确认是否删除!',
          type: 'warning',
          showCancelButton: true,
          confirmButtonText: '删除',
          cancelButtonText: '不删除',
          }).then(function(isConfirm) {
          if (isConfirm === true) {
          	postDel(data);
              swal(
  	            '删除',
  	            '删除成功',
  	            'success'
              );
          }
    }); 
})
// 删除
function postDel(param){
    fetchPost({
        url:"/backCompany/companyDel",
        params: param,
        callback:function(data){
            console.log(data);
            fetchList();
        }
    })
}

// 查看
$(document).on("click",".cat",function(){
    var $row = $(this).parents("tr");
    
    var comLogo = $row.data("com-logo");
    var comLicense = $row.data("com-license");
    
    var $obj = $("#browseModal").find(".showValue");
    $obj.eq(0).html($row.data("com-name"));
    $obj.eq(1).html($row.data("type-name"));
    $obj.eq(2).html($row.data("scale"));
    $obj.eq(3).html($row.data("com-desc"));
    $obj.eq(4).html($row.data("location"));
    $obj.eq(5).html($row.data("add-detail"));
    $obj.eq(6).html($row.data("com-person"));
    $obj.eq(7).html($row.data("com-phone"));
   
    $obj.eq(8).html($row.data("com-email"));
    $obj.eq(9).attr("src",comLogo);
    $obj.eq(10).attr("src",comLicense);
    $('#browseModal').modal('toggle');
})

// 打开 修改modal
$(document).on("click",".openUpdateModal",function(){
	$('select[data-type="area2"]').change(function(){ // 地区选择
		areaConfig2($(this).attr("id"),$(this).val()); // 地区
	});
	
    var $row = $(this).parents("tr");
    var comName = $row.data("com-name");
    var companyTypeId = $row.data("company-type-id");
    var scale = $row.data("scale");
    var scaleId = $row.data("scale-id");
    var comDesc = $row.data("com-desc");
    var addrDetail = $row.data("add-detail");
    var comtPerson = $row.data("com-person");
    var comPhone = $row.data("com-phone");
    var comLogo = $row.data("com-logo");
    var comLicense = $row.data("com-license");
    var comEmail = $row.data("com-email");
    var hpCompanyId = $row.data("company-id");
    var countyId = $row.data("county-id");
    var cityId = $row.data("city-id");
    var provinceId = $row.data("province-id");
    var provinceName = $row.data("province-name");
    var cityName = $row.data("city-name");
    var countyName = $row.data("county-name");
    
    $("#hpCompanyId2").val(hpCompanyId);
    $("#comName2").val(comName);
    $("#companyTypeId2").val(companyTypeId);
    $("#companyScaleId2").val(scaleId);
    $("#comDesc2").val(comDesc);
    $("#province2").val(provinceId);
	$("#city2").append('<option value="'+cityId+'" selected >'+cityName+'</option>');
	$("#county2").append('<option value="'+countyId+'" selected >'+countyName+'</option>');
	
    $("#addrDetail2").val(addrDetail);
    $("#comtPerson2").val(comtPerson);
    $("#comPhone2").val(comPhone);
    $("#comLicense2").val(comLicense);
    $("#comEmail2").val(comEmail);
    
    $("#comLogo2").attr("src",comLogo);
    $("#comlicens2").attr("src",comLicense);
    
    $('#updateCompanyModal').modal('show');
})

//修改 提交
$(document).on("click","#updateComfirm",function(){
	
	var hpCompanyId = $("#hpCompanyId2").val();
	var comName = $("#comName2").val();
    var companyTypeId = $("#companyTypeId2").val();
    var companyScaleId = $("#companyScaleId2").val();
    var comDesc = $("#comDesc2").val();
    var countyId = $("#county2").val();
    var addrDetail = $("#addrDetail2").val();
    var comtPerson = $("#comtPerson2").val();
    var comPhone = $("#comPhone2").val();
    var comEmail = $("#comEmail2").val();
    
    var saveParams ={};
    saveParams.comName = comName ;
    saveParams.companyTypeId = companyTypeId ;
    saveParams.companyScaleId = companyScaleId ;
    saveParams.comDesc = comDesc ;
    saveParams.countyId = countyId ;
    saveParams.addrDetail = addrDetail ;
    saveParams.comtPerson = comtPerson ;
    saveParams.comPhone = comPhone ;
    saveParams.comEmail = comEmail ;
    saveParams.companyId = hpCompanyId ;
    
    fetchPost({
        url:"/backCompany/updateCompany",
        params: saveParams,
        callback:function(data){
            console.log(data);
            fetchList();
            $('#updateCompanyModal').modal('hide');
            swal(
	                   'Saved!',
	                   '已修改.',
	                   'success'
	           );
        }
    })
   
})


$(document).on("click","#openAddCompany",function(){
    $('#addCompanyModel').modal('toggle');
})

//新增 企业
$(document).on("click","#newCompany",function(){
    var saveParams ={} ;
    saveParams.comName = $("#comName").val();
    saveParams.companyTypeId = $("#companyTypeId").val();
    saveParams.companyScaleId = $("#companyScaleId").val();
    saveParams.comDesc = $("#comDesc").val();
    saveParams.countryId = $("#county").val();
    saveParams.addrDetail =$("#addrDetail").val();
    saveParams.comtPerson = $("#comtPerson").val();
    saveParams.comPhone = $("#comPhone").val();
    saveParams.comEmail = $("#comEmail").val();
    
    //保存图片
    saveParams.comLogo = comLogo ;
    saveParams.comLicense = comLicense ;
    
    fetchPost({
        url:"/backCompany/newCompany",
        params: saveParams,
        callback:function(data){
            fetchList();
        }
    })
   $('#addCompanyModel').modal('hide');
})
 
var comLogo ;


var comLicense ;


var listParams = {
	comName:"",
    startTime:"",
    endTime:"",
    currentPage:1,
    showCount:15
}
var list= [];
var totalPage=1;

fetchList();
$(function(){
	positionConfig();
	$('select[data-type="area"]').change(function(){ // 地区选择
		areaConfig($(this).attr("id"),$(this).val()); // 地区
	});
})
function positionConfig(){
	areaConfig(null,null); // 地区
}
function areaConfig(areaType,areaId){
	var provinceId = null;
	var cityId = null;
	if(areaType == "province"){ // 省查询市
		provinceId = areaId;
		$("#county").html('<option>全部</option>');
	}else if(areaType == "city"){ // 市查询区县
		cityId = areaId;
	}else if(areaType == "county"){
		return;
	}
	fetchGet({
		url:apiData.area,
		params:{
			provinceId:provinceId,
			cityId:cityId,
		},
		callback:function(data){
			if(data){
				var content = '<option>全部</option>';
				var list = data.list;
				var length = list.length;
				for(var i=0;i<length;i++){
					content += '<option value="'+list[i].areaId+'" >'+list[i].areaName+'</option>';
				}
				if(content !=''){
					if(areaType == "province"){ // 省查询市
						$("#city").html(content);
					}else if(areaType == "city"){ // 市查询区县
						cityId = areaId;
						$("#county").html(content);
					}else if(areaType == "county"){
						return;
					}else{
						$("#province").html(content);
					}
					
				}
			}
		}
	});
}

$(function(){
	positionConfig2();
	$('select[data-type="area2"]').change(function(){ // 地区选择
		areaConfig2($(this).attr("id"),$(this).val()); // 地区
	});
})
function positionConfig2(){
	areaConfig2(null,null); // 地区
}
function areaConfig2(areaType,areaId){
	var provinceId = null;
	var cityId = null;
	if(areaType == "province2"){ // 省查询市
		provinceId = areaId;
		$("#county2").html('<option>全部</option>');
	}else if(areaType == "city2"){ // 市查询区县
		cityId = areaId;
	}else if(areaType == "county2"){
		return;
	}
	fetchGet({
		url:apiData.area,
		params:{
			provinceId:provinceId,
			cityId:cityId,
		},
		callback:function(data){
			if(data){
				var content = '<option>全部</option>';
				var list = data.list;
				var length = list.length;
				for(var i=0;i<length;i++){
					content += '<option value="'+list[i].areaId+'" >'+list[i].areaName+'</option>';
				}
				if(content !=''){
					if(areaType == "province2"){ // 省查询市
						$("#city2").html(content);
					}else if(areaType == "city2"){ // 市查询区县
						cityId = areaId;
						$("#county2").html(content);
					}else if(areaType == "county2"){
						return;
					}else{
						$("#province2").html(content);
					}
					
				}
			}
		}
	});
}


// 获取table数据
function fetchList(){
    fetchGet({
        url:"/backCompany/companyList",
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
function postAuth(data){
    fetchPost({
        url:"/backCompany/companyAuth",
        params: data,
        callback:function(data){
            console.log(data);
            fetchList();
            
        }
    })
}


function addTableList(list){
    var $table = $("#userList");
    var $tBody = $table.find("tbody");
    var templeteTr="";
    list.forEach(function(item){
        templeteTr+='\
        <tr \
        	data-company-id="'+ item.hpCompanyId +'" \
            data-com-name="'+ item.comName +'" \
            data-company-type-id="'+ item.hpCompanyTypeId +'" \
            data-type-name="'+ item.typeName +'" \
            data-scale="'+ scale(item.lowerNum,item.hightNum) +'" \
            data-county-id="'+ item.countyId +'" \
            data-city-id="'+ item.cityId +'" \
            data-province-id="'+ item.provinceId +'" \
            data-province-name="'+ item.provinceName +'" \
            data-city-name="'+ item.cityName +'" \
            data-county-name="'+ item.countyName +'" \
            data-location="'+item.provinceName+item.cityName+item.countyName +'"\
            data-scale-id="'+ item.hpCompanyScaleId +'" \
            data-com-desc="'+ item.comDesc +'" \
            data-add-detail="'+ item.addrDetail +'" \
            data-com-person="'+ item.comtPerson +'" \
            data-com-phone="'+ item.comPhone +'" \
            data-com-email="'+ item.comEmail +'" \
            data-com-logo="'+ item.comLogo +'" \
            data-com-license="'+ item.comLicense +'" >\
            <th>'+ item.comName +'</th>\
            <th>'+ isNull(item.typeName) +'</th>\
            <th>'+ scale(item.lowerNum,item.hightNum) +'</th>\
            <th>'+ isNull(item.comtPerson) +'</th>\
            <th>'+ isNull(item.comPhone) +'</th>\
            <th>'+ isNull(item.comEmail) +'</th>\
            <th>'+ approveState(item.approveState) +'</th>\
            <th>'+ timestampToTime(item.createTime) +'</th>\
            <th>\
                <button type="button" class="btn btn-default btn-sm cat">查看</button>\
                <button type="button" class="btn btn-primary btn-sm openUpdateModal">编辑</button>\
            	<button type="button" class="btn btn-success btn-sm  auth">认证</button>\
            	<button type="button" class="btn btn-danger  btn-sm  del">删除</button>\
            </th>\
        </tr>';
    })
    $tBody.html(templeteTr)    
}
function scale(lowerNum,hightNum){
	if(lowerNum == 0){
		return hightNum +"人以下";
	}else if(hightNum == 0){
		return lowerNum +"人以上";
	}else{
		return lowerNum +"-"+hightNum+"人" ;
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

function change(t) {
    if (t < 10) {
        return "0" + t;
    } else {
        return t;
    }
}
//判断是否认证
function approveState(value){
    switch (value) {
        case 0:return "未认证"
        case 1:return "认证通过"
        case 2:return "认证不通过"
        case 3:return "认证待审核"
    }
}

//判断是否认证
//function scale2(value){
//    switch (value) {
//        case 1:return "20人以下"
//        case 2:return "20-99人"
//        case 3:return "100-499人"
//        case 4:return "500-999人"
//        case 5:return "1000-9999人"
//        case 6:return "10000人以上"
//        default: return "20人以下"
//    }
//}

