 package com.happy.service.message;

import com.happy.plugin.BaseMsg;

public interface MessageService {
     
    /**
     * @TODO-推送微信消息
     */
    BaseMsg pushWxMsg(String content);
    
    
    /**
     * -活动所有模板信息
     * Description:  
     * @author jiangchao1  
     * @date 2019年1月2日  
     * @version 1.0
     */
    BaseMsg getAllWxTemplateIds(Integer currentPage,Integer showCount);
    
}
