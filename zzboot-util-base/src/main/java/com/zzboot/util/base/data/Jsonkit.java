package com.zzboot.util.base.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class Jsonkit {

    private static final SerializeConfig config;
    static {
        config = new SerializeConfig();
        // config.put (java.util.Date.class, new JSONLibDataFormatSerializer ()); // 使用和json-lib兼容的日期输出格式
        // config.put (java.sql.Date.class, new JSONLibDataFormatSerializer ()); // 使用和json-lib兼容的日期输出格式
    }
    private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
                                                      // SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat
                                                      // SerializerFeature.WriteClassName // 序列化为和JSON-LIB兼容的字符串
                                                      };

    /**
     *
     * @param object
     * @param propertyFilter
     * @param nameFilter
     * @param isToCamelCase
     * @param isToBooleanToInt
     * @return
     */
    public static String object2JsonString(Object object, final List<String> propertyFilter, final Map<String, String> nameFilter, boolean isToCamelCase, boolean isToBooleanToInt){
        PropertyFilter pf = new PropertyFilter() {

            @Override
            public boolean apply(Object object, String name, Object value){
                if (propertyFilter.contains (name)) {
                    return false;
                }
                return true;
            }
        };
        NameFilter _nameFilter = new NameFilter() {

            @Override
            public String process(Object object, String name, Object value){
                if (nameFilter.containsKey (name)) {
                    name = nameFilter.get (name);
                }
                return name;
            }
        };
        NameFilter toCamelCase = new NameFilter() {

            @Override
            public String process(Object object, String name, Object value){
                if (name.indexOf (StringFormatKit.SEPARATOR) > -1) {
                    name = StringFormatKit.toCamelCase (name);
                }
                return name;
            }
        };

        ValueFilter toBooleanToInt = new ValueFilter() {

            @Override
            public Object process(Object object, String name, Object value){
                if (value instanceof Boolean) {
                    boolean _value = (Boolean) value;
                    if (_value == Boolean.TRUE) {
                        return "1";
                    }else {
                        return "0";
                    }
                }
                return value;
            }
        };
        JSONSerializer serializer = new JSONSerializer(config);

        if (null != propertyFilter && propertyFilter.size () > 0) {
            // 增加字段过滤
            serializer.getPropertyFilters ().add (pf);
        }
        if (null != nameFilter && nameFilter.size () > 0) {
            // 增加字段转换
            serializer.getNameFilters ().add (_nameFilter);
        }
        if (isToCamelCase) {
            // 增加下划线转驼峰
            serializer.getNameFilters ().add (toCamelCase);
        }
        if (isToBooleanToInt) {
            // 增加blean转int
            serializer.getValueFilters ().add (toBooleanToInt);
        }
        for ( SerializerFeature feature : features ) {
            serializer.config (feature, true);
        }
        serializer.write (object);
        return serializer.toString ();
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性
     * @param object
     * @param propertyFilter
     *            过滤不需要的属性
     * @return
     */
    public static String object2JsonString(Object object, List<String> propertyFilter){
        return object2JsonString (object, propertyFilter, null, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以重命名某些元素
     * @param object
     * @param nameFilter
     *            需要重新命名的属性
     * @return
     */
    public static String object2JsonString(Object object, Map<String, String> nameFilter){
        return object2JsonString (object, null, nameFilter, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description:转json时下划线可以转驼峰
     * @param object
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object, boolean isToCamelCase){
        return object2JsonString (object, null, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性，并重命名某些属性
     * @param object
     * @param propertyFilter
     *            过滤不需要的属性
     * @param nameFilter
     *            重命名某些属性
     * @return
     */
    public static String object2JsonString(Object object, List<String> propertyFilter, final Map<String, String> nameFilter){
        return object2JsonString (object, propertyFilter, nameFilter, false, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 可以过滤不需要的属性,下划线可以转驼峰
     * @param object
     * @param propertyFilter
     *            可以过滤不需要的属性
     * @param isToCamelCase
     *            true 下划线可以转驼峰
     * @return
     */
    public static String object2JsonString(Object object, List<String> propertyFilter, boolean isToCamelCase){
        return object2JsonString (object, propertyFilter, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持重命名某些属性，支持下划线可以转驼峰
     * @param object
     * @param nameFilter
     *            重命名某些属性
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object, Map<String, String> nameFilter, boolean isToCamelCase){
        return object2JsonString (object, null, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰，支持boolean 转true=1，false=0
     * @param object
     * @param isToCamelCase
     *            true 支持下划线转驼峰
     * @param isToBooleanToInt
     *            true 支持boolean转数字
     * @return
     */
    public static String object2JsonString(Object object, boolean isToCamelCase, boolean isToBooleanToInt){
        return object2JsonString (object, null, null, isToCamelCase, isToBooleanToInt);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰，支持属性过滤，支持重命名
     * @param object
     * @param propertyFilter
     *            需要过滤的属性
     * @param nameFilter
     *            需要重命名的属性
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object, final List<String> propertyFilter, final Map<String, String> nameFilter, boolean isToCamelCase){
        return object2JsonString (object, propertyFilter, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰， 支持重命名,支持boolean 转true=1，false=0
     * @param object
     * @param propertyFilter
     *            需要过滤的属性
     * @param isToBooleanToInt
     *            true 支持boolean转int
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object, List<String> propertyFilter, boolean isToCamelCase, boolean isToBooleanToInt){
        return object2JsonString (object, propertyFilter, null, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: 支持下划线转驼峰， 支持属性过滤,支持boolean 转true=1，false=0
     * @param object
     * @param nameFilter
     *            属性过滤
     * @param isToBooleanToInt
     *            true 支持boolean转int
     * @param isToCamelCase
     *            true 下划线转驼峰
     * @return
     */
    public static String object2JsonString(Object object, Map<String, String> nameFilter, boolean isToCamelCase, boolean isToBooleanToInt){
        return object2JsonString (object, null, nameFilter, isToCamelCase, false);
    }

    /**
     * @Title: object2JsonString
     * @Description: Object 转json
     * @return
     */
    public static String object2JsonString(Object object){
        return JSON.toJSONString (object, config, features);
    }

    /**
     * @Title: jsonString2Object
     * @Description: josn 转OBject
     * @return
     */
    public static <T> T jsonString2Object(String jsonString, Class<T> classes){
        return jsonString2Object (jsonString, classes, false);
    }

    /**
     *
     * @param jsonString
     * @param clazz
     * @param verify
     * @param <T>
     * @return
     */
    public static <T> T jsonString2Object(String jsonString, Class<T> clazz, boolean verify){
        if (verify && null == String2JSON (jsonString)) {
            return null;
        }
        return JSON.parseObject (jsonString, (Type) clazz);
    }

    /**
     * @Title: jsonString2Array
     * @Description: josn 转List
     * @return
     */
    public static <T> List<T> jsonString2Array(String jsonString, Class<T> classes){
        return jsonString2Array (jsonString, classes, false);
    }

    /**
     *
     * @param jsonString
     * @param classes
     * @param verify
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonString2Array(String jsonString, Class<T> classes, boolean verify){
        if (verify) {
            if (null == String2JSON (jsonString)) {
                return null;
            }
            return JSON.parseArray (jsonString, classes);
        } else {
            return JSON.parseArray (jsonString, classes);
        }
    }

    /**
     * @Title: json2Object
     * @Description:JSONObject 转javaObject
     * @return
     */
    public static <T> T json2Object(JSON json, Class<T> classes){
        return JSON.toJavaObject (json, classes);

    }

    /**
     * bean转JSONObject或jsonArray
     * 
     * @param obj
     * @return
     */
    public static JSON beanToJSONObject(Object obj){
        return (JSON) JSON.toJSON (obj);
    }

    /**
     * @Title: String2JSON
     * @Description: String 转json对像JSONObject ,JSONArray,Integer,String
     * @return
     */
    public static Object String2JSON(String json){
        try {
            return JSON.parse (json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 判断是否是json结构
     */
    public static boolean isJson(String value){
        try {
            JSON.parse (value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    
}
