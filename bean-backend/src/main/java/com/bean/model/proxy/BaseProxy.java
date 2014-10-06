package com.bean.model.proxy;

/**
 * Created by Daniel on 14-8-17.
 */
public class BaseProxy {
    public  enum PersistanceModel{JDBC, JPA}
    protected static PersistanceModel persistanceModel;

    static{
        persistanceModel = PersistanceModel.JDBC;
    }
}
