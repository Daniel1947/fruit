package com.bean.model.persistance;

import com.bean.model.Entity;

/**
 * Created by Daniel on 14-8-17.
 */
public interface EntityReadonly {
    public Entity getState() throws Exception;
}
