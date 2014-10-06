package com.bean.model;

/**
 * Created by Daniel on 14-8-17.
 */
public class Attribute {
    private String name;
    public Attribute(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString ()
    {
        return name;
    }
}
