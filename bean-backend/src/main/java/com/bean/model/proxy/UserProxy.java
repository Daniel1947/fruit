package com.bean.model.proxy;

import com.bean.model.dao.user.UserDAO;
import com.bean.model.data.item.ItemVO;
import com.bean.model.data.user.UserVO;
import com.bean.model.session.user.UserSession;

import javax.naming.InitialContext;
import java.util.List;

/**
 * Created by Daniel on 14-8-17.
 */
public class UserProxy extends BaseProxy{
    public UserProxy() {
        super();
    }

    private static InitialContext initialContext = null;

    /**
     * Junit tester can seed its own initialContext before calling any method on this.
     * @param _initialContext
     */
    public static void setInitialContext(InitialContext _initialContext)
    {
        initialContext = _initialContext;
    }

    public static InitialContext getInitialContext()
    {
        return initialContext;
    }

    public static List<UserVO> getAllUsers()throws Exception{
        if(persistanceModel == PersistanceModel.JDBC){
            UserDAO userDao = new UserDAO();
            return userDao.getAllUsers();
        }else if(persistanceModel == PersistanceModel.JPA){
            UserSession userSession = BeanAppServerProxy.getUserSession();
            return userSession.getAllUsers();
        }
        return null;
    }

    public static UserVO getUserById(Long id) throws Exception {
        if(persistanceModel == PersistanceModel.JDBC){
            UserDAO userDao = new UserDAO();
            return userDao.getUserById(id);
        }else if(persistanceModel == PersistanceModel.JPA){
            //TODO waiting for implementation
            return null;
        }
        return null;
    }

    public static UserVO getAuthorizedUser(String account, String password) throws Exception{
        if(persistanceModel == persistanceModel.JDBC){
            UserDAO userDAO = new UserDAO();
            return userDAO.getAuthorizedUserWithAccountAndPwd(account, password);
        }else if(persistanceModel == persistanceModel.JPA){
            UserSession userSession = BeanAppServerProxy.getUserSession();
            return userSession.getAuthorizedUser(account, password);
        }
        return null;
    }

    public static List<ItemVO> getUserListings(UserVO userVO) throws Exception{
        if(persistanceModel == persistanceModel.JDBC){
            UserDAO userDAO = new UserDAO();
            return userDAO.getUserListings(userVO);
        }else if(persistanceModel == persistanceModel.JPA){
            //TODO waiting for implementation
            return null;
        }
        return null;
    }
}
