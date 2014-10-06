package com.bean.model.session.user;

import com.bean.model.data.user.UserVO;

import java.util.List;

/**
 * Created by Daniel on 14-8-17.
 */
public interface UserSession {
    public List<UserVO> getAllUsers() throws Exception;
    public UserVO getAuthorizedUser(String account, String password) throws Exception;
}
