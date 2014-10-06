package com.bean.model.persistance;

import com.bean.model.Entity;

/**
 * Created by Daniel on 14-8-17.
 */
public interface EntityUpdatable  extends EntityReadonly{
    public void setState(Entity voin) throws Exception;
}