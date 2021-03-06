package com.happy.sqlExMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.happy.entity.HpUserBoundEntity;
import com.happy.entity.HpUserRecommendEntity;
import com.happy.entity.HpUserSearchEntity;
import com.happy.service.message.data.UserApprove;
import com.happy.service.position.data.PositionSearch;
import com.happy.service.user.data.UserSearch;

@Repository("hpUserBoundExMapper")
public interface HpUserBoundExMapper{

    /**
     *
     * @TODO:     根据token获取微信登录信息
     */
    HpUserBoundEntity getBoundByToken(@Param("oid") String oid);
    /**
     *
     * @TODO:     根据token获取微信登录信息记录ID
     */
    Long getBoundIdByToken(@Param("oid") String oid);
    /**
     *
     * @TODO:     根据openId获取微信登录信息
     */
    HpUserBoundEntity getBoundByOpenId(@Param("openId") String openId);
    /**
     *
     * @TODO:     描述一下这个方法是干什么的
     */
    List<HpUserSearchEntity> getUserSearchList(UserSearch page);
    /**
     *
     * @TODO:     描述一下这个方法是干什么的
     */
    int getUserSearchNum(UserSearch page);
    /**
     *
     * @TODO:     描述一下这个方法是干什么的
     */
    void updateUserSearchDel(@Param("oid")String oid, @Param("hpUserSearchId")Long hpUserSearchId);
    /**
     *
     * @TODO:     描述一下这个方法是干什么的
     */
    HpUserRecommendEntity getRecdByOid(@Param("oid")String oid);
    /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   HpUserRecommendEntity getRecdByPhoneNo(@Param("phoneNo")String phoneNo);
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   HpUserRecommendEntity getRecdByUserId(@Param("hpUserId")Long hpUserId);
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   String getSessionKey(@Param("oid")String oid);
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   String getFormIdByToken(@Param("oid")String oid);
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   UserApprove getSendDataByUserKey(@Param("hpUserId")Long hpUserId);
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    */
   void updateBoundFormId(@Param("oid")String oid,@Param("formId")String formId);
   
   void updateUserPicByUserId(@Param("hpUserId")Long hpUserId);
   
   List<HpUserBoundEntity> getGroupBound(PositionSearch page);
}
