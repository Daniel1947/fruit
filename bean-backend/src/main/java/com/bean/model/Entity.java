package com.bean.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Daniel on 14-8-17.
 */
public abstract class Entity implements Serializable, Comparable{
    public static final String ID = "Id";
    protected Map m_attributes;

    public Entity() {
        this(null);
    }

    public Entity(Object _id) {
        this.setAttribute(ID, _id);
    }

    /**
     *  Get primary key
     */
    public Object getId() {
        return this.getAttribute(ID);
    }

    /**
     *  Set primary key
     */
    public void setId(Object _id) {
        this.setAttribute(ID, _id);
    }

    /**
     * validate the values of this object before used for updating or creation
     * to be override by subclasses
     */
    public void validate(){// throws DataException {
    }

    /**
     *  Get attribute value by attribute name.
     *  It is better practice to use
     *@see this.getAttribute(  Attribute )
     */
    public Object getAttribute(String attribute) {
        if (m_attributes == null)
            return null;
        return m_attributes.get(attribute);
    }

    /**
     *  Get attribute value to attribute.
     */
    public Object getAttribute(Attribute attribute) {
        return getAttribute(attribute.getName());
    }

    /**
     *  Get attribute value to attribute.
     */
    public Long getAttributeAsLong(Attribute attribute) {
        return convert2Long(getAttribute(attribute.getName()));
    }

    /**
     *  Get attribute value to attribute.
     */
    public Date getAttributeAsDate(Attribute attribute) {
        return convert2Date(getAttribute(attribute.getName()));
    }

    /**
     *  Get attribute value to attribute.
     */
    public String getAttributeAsString(Attribute attribute) {
        return convert2String(getAttribute(attribute.getName()));
    }


    /**
     *  Set attribute value by attribute name.
     *  It is better practice to use
     *@see this.setAttribute(  Attribute , Object)
     */
    public void setAttribute(String attribute, Object value) {
        if (m_attributes == null)
            m_attributes = new HashMap();
        m_attributes.put(attribute, value);
    }

    /**
     *  Set attribute value.
     */
    public void setAttribute(Attribute attribute, Object value) {
        setAttribute(attribute.getName(), value);
    }

    /**
     *  Get all attributes.
     */
    @JsonIgnore
    public Map getAttributes() {
        return m_attributes;
    }

    /**
     *  Set attributes.
     */
    public void setAttributes(Map map) {
        m_attributes = map;
    }

    /**
     *  Remove attributes.
     */
    public void removeAttribute(Attribute attribute) {
        if (m_attributes == null)
            return;
        m_attributes.remove(attribute.getName());
    }

    @JsonIgnore
    public abstract String getPrimaryKeyAsString();

    public int compareTo(Object object) {
        if (!this.getClass().getName().equals(object.getClass().getName()))
            return -1;
        Entity that = (Entity)object;
        return (this.getPrimaryKeyAsString()).compareTo(that.getPrimaryKeyAsString());
    }

    /**
     * Fix json convert type missing
     * @param number
     * @return
     */
    public Long convert2Long(Object number) {
        Long convertedNumber = null;
        if (number instanceof Long) {
            convertedNumber = (Long)number;
        } else if (number instanceof Integer) {
            convertedNumber = ((Integer)number).longValue();
        } else if (number instanceof String) {
            convertedNumber = Long.parseLong((String)number);
        }
        return convertedNumber;
    }

    /**
     * Fix json convert type missing
     * @param number
     * @return
     */
    public Double convert2Double(Object number) {
        Double convertedNumber = null;
        if (number instanceof Double)
            convertedNumber = (Double)number;
        if (number instanceof Integer)
            convertedNumber = new Double((Integer)number);
        if (number instanceof String)
            convertedNumber = new Double((String)number);
        return convertedNumber;
    }


    /**
     * Fix json convert type error
     * @param date
     * @return
     */
    public Date convert2Date(Object date) {
        Date convertedDate = null;
        if (date instanceof Date) {
            convertedDate = (Date)date;
        } else if (date instanceof Long) {
            convertedDate = new Date((Long)date);
        } else if (date instanceof String) {
            try {
                convertedDate = (new SimpleDateFormat()).parse((String)date);
            } catch (ParseException e) {
                //TODO log
                e.printStackTrace();
            }
        }

        return convertedDate;
    }

    /**
     * Fix json convert type error
     * @param obj
     * @return
     */
    public String convert2String(Object obj) {
        String str = null;
        if (obj == null) {
            str = null;
        } else {
            str = obj.toString();
        }
        return str;
    }

    /**
     *  For debugging.
     */
    public String toString() {
        StringBuffer result = new StringBuffer("\n" + this.getClass().getName());
        result.append(this.getClass().getName()).append("\n");
        Iterator it = m_attributes.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = m_attributes.get(key) == null ? "NULL" : m_attributes.get(key).toString();
            result.append("\tAttName=").append(key.toString()).append("\t\tAttValue=").append(value).append("\n");
        }
        return result.toString();
    }
}
