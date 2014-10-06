package com.bean.model.persistance.jdbc.user;

import com.bean.model.Entity;
import com.bean.model.data.user.UserIF;
import com.bean.model.data.user.UserVO;
import com.bean.model.persistance.EntityUpdatable;
import com.bean.model.persistance.PersistanceUtil;

import java.io.Serializable;

/**
 * Created by Daniel on 14-8-17.
 */
public class User implements UserIF, EntityUpdatable, Serializable{
    private String userLoginName;
    private String userLoginPwd;
    private String userDname;
    private String userMobile;
    private String userEmail;
    private Long userId;

    @Override
    public String getUserLoginName() {
        return userLoginName;
    }

    @Override
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Override
    public String getUserLoginPwd() {
        return userLoginPwd;
    }

    @Override
    public void setUserLoginPwd(String userLoginPwd) {
        this.userLoginPwd = userLoginPwd;
    }

    @Override
    public String getUserDname() {
        return userDname;
    }

    @Override
    public void setUserDname(String userDname) {
        this.userDname = userDname;
    }

    @Override
    public String getUserMobile() {
        return userMobile;
    }

    @Override
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Override
    public String getUserEmail() {
        return this.userEmail;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public void setState(Entity voin) throws Exception {
        PersistanceUtil.setEBState(this, voin);
    }

    @Override
    public Entity getState() throws Exception {
        UserVO vo = new UserVO();
        PersistanceUtil.setVOState(this, vo);
        return vo;
    }
}
