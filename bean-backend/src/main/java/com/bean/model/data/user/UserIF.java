package com.bean.model.data.user;

/**
 * Created by Daniel on 14-8-17.
 */
public interface UserIF {

    public String getUserLoginName();

    public void setUserLoginName(String userLoginName);

    public String getUserLoginPwd();

    public void setUserLoginPwd(String userLoginPwd);

    public String getUserDname();

    public void setUserDname(String userDname);

    public String getUserMobile();

    public void setUserMobile(String userMobile);

    public String getUserEmail();

    public void setUserEmail(String userEmail);

    public Long getUserId();

    public void setUserId(Long userId);
}
