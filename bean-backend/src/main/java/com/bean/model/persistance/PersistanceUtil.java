package com.bean.model.persistance;

import com.bean.model.Entity;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Daniel on 14-8-17.
 */
public class PersistanceUtil {
    private static Logger logger = Logger.getLogger(PersistanceUtil.class.getName());
    public static Entity setVOState(Object eb, Entity vo) throws Exception {
        try {
            if (eb == null)
                return null;

            Class[] interfaces = eb.getClass().getInterfaces();
            Class ebClass = interfaces[0];
            Method[] ebMethods = ebClass.getMethods();
            Method ebMethod = null;
            String ebMethodName = null;
            String attributeName = null;
            Object value = null;
            for (int i = 0; i < ebMethods.length; i++) {
                ebMethod = ebMethods[i];
                ebMethodName = ebMethod.getName();
                int index = ebMethodName.indexOf("get");
                if (index < 0)
                    continue;
                if (ebMethodName.indexOf("getPrimaryKey") != -1)
                    continue;
                attributeName = ebMethodName.substring(index + 3);
                String returnType = ebMethod.getReturnType().getName();
                if (returnType.equals("void"))
                    continue;
                if (returnType.equals("Collection") || (returnType.indexOf(".Collection") != -1))
                    continue;
                if (returnType.equals("Set") || (returnType.indexOf(".Set") != -1))
                    continue;
                if (returnType.indexOf("java.lang") == -1 && (returnType.indexOf("java.util") == -1))
                    continue;

                value = ebMethod.invoke(eb, null);

                vo.setAttribute(attributeName, value);
            }
            return vo;
        } catch (Exception e) {
            //TODO LOG HERE
            throw e;
        }
    }

    public static void setEBState(Object eb, Entity vo) throws Exception {
        String attributeName = null;
        try {
            Map map = vo.getAttributes();
            if (map == null || map.isEmpty())
                return;
            Class[] interfaces = eb.getClass().getInterfaces();
            Class ebClass = interfaces[0];
            Method getMethod = null;
            Method setMethod = null;
            Iterator keys = map.keySet().iterator();
            StringBuffer methodName = null;
            Object voTypedValue = null;
            Object ebValue = null;
            Class type = null;
            boolean changed = false;
            while (keys.hasNext()) {
                attributeName = (String) keys.next();
                if (attributeName.equals(Entity.ID))
                    continue;
                methodName = new StringBuffer("get").append(attributeName);
                try {
                    getMethod = ebClass.getMethod(methodName.toString(), null);
                    type = getMethod.getReturnType();
                    String paramType = type.getName();
                    if (paramType.equals("Collection") || (paramType.indexOf(".Collection") != -1))
                        continue;
                    if (paramType.equals("Set") || (paramType.indexOf(".Set") != -1))
                        continue;
                    if (paramType.indexOf("java.lang") == -1 && (paramType.indexOf("java.util") == -1))
                        continue;
                } catch (Exception ex) {
                    //TODO LOG HERE
                    continue;
                }
                ebValue = getMethod.invoke(eb, null);
                Object attributeValue = map.get(attributeName);
                if (attributeValue == null)
                    continue;
                methodName = new StringBuffer("set").append(attributeName);
                setMethod = ebClass.getMethod(methodName.toString(), new Class[] { type });
                voTypedValue = getTypedAttribute(vo, attributeName, setMethod);
                if (ebValue == null && voTypedValue instanceof String && ((String) voTypedValue).length() == 0) {
                    continue;
                }
                if (voTypedValue != null && !voTypedValue.equals(ebValue)) {
                    setMethod.invoke(eb, new Object[] { voTypedValue });
                    changed = true;
                }
            }
//            if (logger.isFineEnabled())
//                logger.fine("ServerUtil.setEBState for UpdateOnlyWhenChanged. Entity change " +
//                                                                           changed + " " + ebClass + " id=" +
//                                                                           eb.getId());
        } catch (NoSuchMethodException e) {
            //TODO LOG HERE
            throw e;
        } catch (Exception e) {
            //TODO LOG HERE
            throw e;
        }

    }

    private static Object getTypedAttribute(Entity vo, String attributeName, Method method) throws Exception {
        Object value = vo.getAttribute(attributeName);
        if (value == null)
            return null;
        Class[] paramTypes = method.getParameterTypes();
        if (paramTypes.length != 1)
            return value;
        Class paramType = paramTypes[0];
        if (paramType.equals(Class.forName("java.lang.String"))) {
            if (value instanceof String)
                return value;
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Boolean"))) {
            if (value instanceof Boolean)
                return value;
            if (value instanceof String)
                return new Boolean((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Long"))) {
            if (value instanceof Long)
                return value;
            if (value instanceof Integer)
                return new Long((Integer) value);
            if (value instanceof String)
                return new Long((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Double"))) {
            if (value instanceof Double)
                return value;
            if (value instanceof Integer)
                return new Long((Integer) value);
            if (value instanceof String)
                return new Double((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Float"))) {
            if (value instanceof Float)
                return value;
            if (value instanceof String)
                return new Float((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Integer"))) {
            if (value instanceof Integer)
                return value;
            if (value instanceof String)
                return new Integer((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.lang.Short"))) {
            if (value instanceof Short)
                return value;
            if (value instanceof String)
                return new Short((String) value);
            throw new Exception("incorrect data type");
        } else if (paramType.equals(Class.forName("java.util.Date"))) {
            if (value instanceof Date)
                return value;
            if (value instanceof Long)
                return new Date((Long)value);
            if (value instanceof String)
                return (new SimpleDateFormat()).parse((String) value);
            throw new Exception("incorrect data type");
        } else
            throw new Exception("incorrect data type");
    }
}
