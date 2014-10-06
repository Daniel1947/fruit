package com.bean.model.data.user;

import com.bean.model.Entity;

/**
 * Created by Daniel on 14-8-17.
 */
public class UserVO  extends Entity implements UserIF{
    @Override
    public String getPrimaryKeyAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append((String)this.getAttribute(UserAttributes.userId)).append("_");
        return sb.toString();
    }

    @Override
    public String getUserLoginName() {
        return (String)this.getAttribute(UserAttributes.userLoginName);
    }

    @Override
    public void setUserLoginName(String userLoginName) {
        this.setAttribute(UserAttributes.userLoginName, userLoginName);
    }

    @Override
    public String getUserLoginPwd() {
        return (String)this.getAttribute(UserAttributes.userLoginPwd);
    }

    @Override
    public void setUserLoginPwd(String userLoginPwd) {
        this.setAttribute(UserAttributes.userLoginPwd, userLoginPwd);
    }

    @Override
    public String getUserDname() {
        return (String)this.getAttribute(UserAttributes.userDname);
    }

    @Override
    public void setUserDname(String userDname) {
        this.setAttribute(UserAttributes.userDname, userDname);
    }

    @Override
    public String getUserMobile() {
        return (String)this.getAttribute(UserAttributes.userMobile);
    }

    @Override
    public void setUserMobile(String userMobile) {
        this.setAttribute(UserAttributes.userMobile, userMobile);
    }

    @Override
    public String getUserEmail() {
        return (String)this.getAttribute(UserAttributes.userEmail);
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.setAttribute(UserAttributes.userEmail, userEmail);
    }

    @Override
    public Long getUserId() {
        return (Long)this.getAttribute(UserAttributes.userId);
    }

    @Override
    public void setUserId(Long userId) {
        this.setAttribute(UserAttributes.userId, userId);
    }
}
