package com.bean.model.proxy;

import com.bean.model.session.user.UserSession;

import javax.naming.InitialContext;
import java.util.logging.Logger;

/**
 * Created by Daniel on 14-8-17.
 */
public class BeanAppServerProxy  extends BeanAppServerProxyBase{

    private static final Logger s_log = Logger.getLogger(BeanAppServerProxy.class.getName());

    private static InitialContext initialContextASP = null;

    /**
    * Junit tester can seed its own initialContext before calling any method on this.
    * @param initialContext
    */
    public static void setInitialContext(InitialContext initialContext)
    {
        BeanAppServerProxy.initialContextASP = initialContext;
    }

    public static InitialContext getInitialContext()
    {
        return initialContextASP;
    }
    public static UserSession getUserSession() throws Exception
    {
        return getUserSession(initialContextASP);
    }
}
