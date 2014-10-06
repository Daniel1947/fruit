package com.bean.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Created by Daniel on 14-8-17.
 */
public class EjbClientLocator {
    private static Logger logger = Logger.getLogger(EjbClientLocator.class.getName());

    protected static EjbClientLocator s_EjbClientLocator;
    protected InitialContext m_initialContext;

    protected EjbClientLocator() {
        super();
    }

    public static EjbClientLocator getInstance() {
        if (s_EjbClientLocator == null) {
            synchronized (EjbClientLocator.class) {
                if (s_EjbClientLocator == null) {
                    s_EjbClientLocator = new EjbClientLocator();
                }
            }
        }
        return s_EjbClientLocator;
    }
    public DataSource getDataSource() throws Exception {
        return null;
    }

    public Object findRemoteEJBHome(String jndiName) throws Exception{
        return this.getInitialContext().lookup(jndiName);
    }

    public InitialContext getInitialContext() throws NamingException {
        if (m_initialContext == null) {
            InitialContext result = null;
            try {
                result = new InitialContext();
                m_initialContext = result;
            } catch (NamingException e) {
                logger.severe(e.getMessage());
                throw e;
            }
        }
        return m_initialContext;
    }

}
