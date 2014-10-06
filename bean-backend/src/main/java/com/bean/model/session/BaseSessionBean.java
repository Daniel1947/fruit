package com.bean.model.session;

import com.bean.model.Entity;
import com.bean.model.persistance.EntityReadonly;
import org.hibernate.exception.DataException;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Daniel on 14-8-17.
 */
public class BaseSessionBean {
    private static final Logger logger = Logger.getLogger(BaseSessionBean.class.getName());

    @Resource
    protected SessionContext sessionContext;
    @PersistenceContext(unitName = "BeanCommon")
    protected EntityManager em;

    protected void throwException(Throwable e) throws Exception {
        if (e instanceof DataException)
            throwException((DataException) e);

        rollback();
        //TODO LOG ERROR
        logger.warning(e.getMessage());
        throw new Exception(e);
    }
    protected void throwException(DataException e) throws Exception {
        rollback();
        //TODO LOG ERROR
        throw e;
    }
    protected void rollback() {
        try {
            if (!sessionContext.getRollbackOnly()) {
                sessionContext.setRollbackOnly();
            }
        } catch (IllegalStateException e) {
            //TODO LOG
            // do nothing: it means that method been deployed as
            // non-transactional.
        }
    }
    protected List<? extends Entity> getVoList(List<? extends EntityReadonly> ebList) throws Exception{
        List<Entity> voList = null;
        if(ebList!=null&&ebList.size()>0){
            voList = new ArrayList<Entity>();
            for(EntityReadonly eb : ebList){
                voList.add(eb.getState());
            }
        }
        return voList;
    }
}
