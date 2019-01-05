package com.happy.controller.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.service.company.CompanyService;
import com.happy.service.company.data.CompanyListMsg;
import com.happy.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="后台企业管理相关请求API",tags="后台企业管理相关请求API")
@RestController
@RequestMapping("backCompany")
public class CompanyManageController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyManageController.class);
    
    @Resource
    private CompanyService companyService;
    
    /**
     * @TODO:    工资列表查询
     */
    @ApiOperation(value="工资列表查询",notes="工资列表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name="comName",value="公司名称，模糊查询",dataType="String",paramType="query",required=false),
        @ApiImplicitParam(name="startTime",value="开始时间",dataType="long",paramType="query",required=false),
        @ApiImplicitParam(name="endTime",value="结束时间",dataType="long",paramType="query",required=false),
    })
    @GetMapping(value="companyList")
    public CompanyListMsg companyListPage(HttpServletRequest request){
    	Long startTime = (Long)Util.typeChange(request.getParameter("startTime"), Long.class);
        Long endTime = (Long)Util.typeChange(request.getParameter("endTime"), Long.class);
        String comName = request.getParameter("comName");
        Integer currentPage = (Integer)Util.typeChange(request.getParameter("currentPage"), Integer.class);
        Integer showCount = (Integer)Util.typeChange(request.getParameter("showCount"), Integer.class);
       
        logger.info("backCompany.companyList 请求参数：comName={},startTime={},endTime={},currentPage={},showCount={}",comName,startTime,endTime,currentPage,showCount);
        CompanyListMsg ss = this.companyService.companyListPage(comName,startTime,endTime,currentPage,showCount);
        return ss ;
    }
}
