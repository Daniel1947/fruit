package com.bean.model.session.user;

import com.bean.model.data.user.UserVO;
import com.bean.model.persistance.jpa.user.User;
import com.bean.model.session.BaseSessionBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 14-8-17.
 */

@Stateless(name = "UserSession",
        mappedName = "Bean-Common-UserSession")
public class UserSessionBean extends BaseSessionBean implements UserSession, UserSessionLocal{
    private EntityManager em;
    @Override
    public List<UserVO> getAllUsers() throws Exception{
        List<User> entities = em.createNamedQuery("User.findAll", User.class).getResultList();
        List<UserVO> result = null;
        for(User entity : entities){
            if(result == null){
                result = new ArrayList<UserVO>();
            }
            result.add((UserVO)entity.getState());
        }
        return result;
    }

    public UserVO getAuthorizedUser(String account, String password) throws Exception{
        //TODO test this method, check whether it really works
        User singleResult = null;
        singleResult = em.createNamedQuery("User.findAll", User.class)
                         .setParameter("t_login_name", account)
                         .setParameter("t_login_pwd", password)
                         .getSingleResult();
        UserVO authorizedUser = (UserVO)singleResult.getState();
        return authorizedUser;
    }
}
