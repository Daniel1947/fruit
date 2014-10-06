package com.bean.model.persistance.jpa.user;

import com.bean.model.data.user.UserIF;
import com.bean.model.data.user.UserVO;
import com.bean.model.persistance.PersistanceUtil;
import com.bean.model.persistance.EntityUpdatable;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Daniel on 14-8-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select o from User o")})
@Table(name = "t_user", schema = "", catalog = "bean")
public class User implements UserIF, EntityUpdatable, Serializable {
    private String userLoginName;
    private String userLoginPwd;
    private String userDname;
    private String userMobile;
    private String userEmail;
    private Long userId;

    @Basic
    @Column(name = "user_login_name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Basic
    @Column(name = "user_login_pwd", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUserLoginPwd() {
        return userLoginPwd;
    }

    public void setUserLoginPwd(String userLoginPwd) {
        this.userLoginPwd = userLoginPwd;
    }

    @Basic
    @Column(name = "user_dname", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUserDname() {
        return userDname;
    }

    public void setUserDname(String userDname) {
        this.userDname = userDname;
    }

    @Basic
    @Column(name = "user_mobile", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Basic
    @Column(name = "user_email", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Id
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (userId != that.userId) return false;
        if (userDname != null ? !userDname.equals(that.userDname) : that.userDname != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (userLoginName != null ? !userLoginName.equals(that.userLoginName) : that.userLoginName != null)
            return false;
        if (userLoginPwd != null ? !userLoginPwd.equals(that.userLoginPwd) : that.userLoginPwd != null) return false;
        if (userMobile != null ? !userMobile.equals(that.userMobile) : that.userMobile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userLoginName != null ? userLoginName.hashCode() : 0;
        result = 31 * result + (userLoginPwd != null ? userLoginPwd.hashCode() : 0);
        result = 31 * result + (userDname != null ? userDname.hashCode() : 0);
        result = 31 * result + (userMobile != null ? userMobile.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + userId.intValue();
        return result;
    }

    @Override
    public void setState(com.bean.model.Entity voin) throws Exception {
        PersistanceUtil.setEBState(this, voin);
    }

    @Override
    public com.bean.model.Entity getState() throws Exception {
        UserVO vo = new UserVO();
        PersistanceUtil.setVOState(this, vo);
        return vo;
    }
}
