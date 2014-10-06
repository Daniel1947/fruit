package com.bean.data;

import java.util.List;

/**
 * Created by Daniel on 14-9-14.
 */
public class ResponseEntityList<T> {
    private int size;
    private List<T> entitis;

    public int getSize() {
        if(entitis == null){
            size = entitis.size();
        }

        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getEntities() {
        return entitis;
    }

    public void setEntities(List<T> entitis) {
        this.entitis = entitis;
    }
}
