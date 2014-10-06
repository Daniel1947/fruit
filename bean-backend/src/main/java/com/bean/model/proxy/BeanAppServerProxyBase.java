package com.bean.model.proxy;

import com.bean.constants.SessionBeanNames;
import com.bean.model.session.user.UserSession;
import com.bean.util.EjbClientLocator;

import javax.naming.InitialContext;

/**
 * Created by Daniel on 14-8-17.
 */
public class BeanAppServerProxyBase {
    private static UserSession userSession;


    private static InitialContext getInitialContext(InitialContext _initialContext) throws Exception{
        InitialContext initialContext = (_initialContext==null)? EjbClientLocator.getInstance().getInitialContext():_initialContext;
        return initialContext;
    }



    public static UserSession getUserSession(InitialContext _initialContext) throws Exception
    {
        InitialContext initialContext = getInitialContext(_initialContext);
        if (userSession == null)
            userSession = (UserSession) initialContext
                    .lookup(SessionBeanNames.User);
        return userSession;
    }
}
