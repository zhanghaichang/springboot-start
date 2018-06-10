package com.dwring.springboot.utils;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author YH
 * @create 2018-06-05
 */
@Slf4j
public class JsonUtils {

    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            //采用默认时间格式输出
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 判断json格式是否正确
     *
     * @param s
     * @return
     */
    public static boolean isJsonCorrect(String s) {
        if (s == null || "[]".equals(s) || "{}".equals(s) || "".equals(s)
                || "[null]".equals(s) || "{null}".equals(s) || "null".equals(s)) {
            return false;
        }
        return true;
    }


    /**
     * 判断是否为JSONObject
     *
     * @param obj instanceof String ? parseObject
     * @return
     */
    public static boolean isJSONObject(Object obj) {
        if (obj instanceof JSONObject) {
            return true;
        }
        if (obj instanceof String) {
            try {
                JSONObject json = parseJsonObject((String) obj);
                return json != null && json.isEmpty() == false;
            } catch (Exception e) {
                log.error("isJSONObject  catch \n" + e.getMessage());
            }
        }

        return false;
    }

    /**
     * 判断是否为JSONArray
     *
     * @param obj instanceof String ? parseArray
     * @return
     */
    public static boolean isJSONArray(Object obj) {
        if (obj instanceof JSONArray) {
            return true;
        }
        if (obj instanceof String) {
            try {
                JSONArray json = parseJsonArray((String) obj);
                return json != null && json.isEmpty() == false;
            } catch (Exception e) {
                log.error("isJSONArray  catch \n" + e.getMessage());
            }
        }

        return false;
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @param cls  cls  返回实例对象类型
     * @return T
     */
    public static <T> T jsonToBean(@NonNull String json, @NonNull Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @param cls  cls  返回实例对象类型
     * @return T
     */
    public static <T> T jsonToBean(@NonNull String json, @NonNull Type cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @return T
     */
    public static <T> T jsonToBean(@NonNull String json) {
        TypeReference typeReference = new TypeReference<T>() {
        };
        return JSON.parseObject(json, typeReference.getType());
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @param cls  cls  返回实例对象类型
     * @return T
     */
    public static <T> T jsonToBean(@NonNull byte[] json, @NonNull Class<T> cls) {
        return JSON.parseObject(json, cls);
    }


    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @param cls  cls  返回实例对象类型
     * @return T     TypeReference typeReference = new TypeReference<JsonBean>() {};
     */
    public static <T> T jsonToBean(@NonNull byte[] json, @NonNull Type cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json 需要转化json字符
     * @return T     TypeReference typeReference = new TypeReference<JsonBean>() {};
     */
    public static <T> T jsonToBean(@NonNull byte[] json) {
        TypeReference typeReference = new TypeReference<T>() {
        };
        return JSON.parseObject(json, typeReference.getType());
    }

    /**
     * 将json字符串转换成java List对象
     *
     * @param json 需要转化json字符
     * @param cls  cls  返回实例对象类型
     * @return List<T>
     */
    public static <T> List<T> jsonToList(@NonNull String json, @NonNull Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj 转化的对象实例
     * @return String  JSON 字符
     */
    public static String beanToJson(@NonNull Object obj) {
        String result = JSON.toJSONString(obj);
        return result;
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj    转化的对象实例
     * @param filter 序列化处理
     * @return String  JSON 字符
     */
    public static String beanToJson(@NonNull Object obj, @NonNull SimplePropertyPreFilter filter) {
        String result = JSON.toJSONString(obj, filter);
        return result;
    }


    /**
     * 价格json字符转换JSONObject
     *
     * @param jsonString 需要转换的字符
     * @return JSONObject
     */
    public static JSONObject parseJsonObject(@NonNull String jsonString) {
        return JSONObject.parseObject(jsonString);
    }

    /**
     * 价格json字符转换JSONObject
     *
     * @param jsonString 需要转换的字符
     * @return JSONObject
     */
    public static JSONArray parseJsonArray(@NonNull String jsonString) {
        return JSONArray.parseArray(jsonString);
    }

    /**
     * 直接获取第一个key相同的string
     *
     * @param payload
     * @param key
     * @return
     */

    public static String getString(String payload, String key) {
        JSONObject payloadJson = parseJsonObject(payload);
        return getString(payloadJson, key);
    }

    public static String getString(JSONObject payloadJson, String key) {
        Set entrySet = payloadJson.entrySet();

        String result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;
            String innerKey = String.valueOf(entryNode.getKey());
            if (innerKey.equals(key) && !(entryNode.getValue() instanceof JSONObject)) {
                return String.valueOf(entryNode.getValue());
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getString(String.valueOf(payloadJson.getString(innerKey)), key);
                    if (StringUtils.isNotBlank(result)) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getString(JSON.toJSONString(jo), key);
                        if (StringUtils.isNotBlank(result)) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }


    /**
     * 通过父、子节点键值对获取值
     *
     * @param payload
     * @param fatherKey
     * @param key
     * @return
     */
    public static String getStringWithFatherKey(String payload, String fatherKey, String key) {
        JSONObject payloadJson = parseJsonObject(payload);
        return getStringWithFatherKey(payloadJson, fatherKey, key, fatherKey);
    }

    public static String getStringWithFatherKey(JSONObject payloadJson, String fatherKey, String key, String originFatherKey) {
        Set entrySet = payloadJson.entrySet();

        String result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;
            String innerKey = String.valueOf(entryNode.getKey());
            if (innerKey.equals(key) && fatherKey.equals(
                    originFatherKey) && !(entryNode.getValue() instanceof JSONObject)) {
                return String.valueOf(entryNode.getValue());
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getStringWithFatherKey((JSONObject) entryNode.getValue(), innerKey, key, originFatherKey);
                    if (StringUtils.isNotBlank(result)) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getStringWithFatherKey((JSONObject) jo, innerKey, key, originFatherKey);
                        if (StringUtils.isNotBlank(result)) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }


    /**
     * 直接获取第一个key相同, 类相同的值
     *
     * @param payload
     * @param key
     * @return
     */
    public static <T> T getValue(String payload, String key, Class<T> clazz) {
        JSONObject payloadJson = JSON.parseObject(payload);
        return getValue(payloadJson, key, clazz);
    }


    public static <T> T getValue(JSONObject payloadJson, String key, Class<T> clazz) {
        Set entrySet = payloadJson.entrySet();

        T result;
        for (Object entry : entrySet) {
            HashMap.Entry entryNode = (HashMap.Entry) entry;
            String innerKey = String.valueOf(entryNode.getKey());

            if (innerKey.equals(key) && clazz.isInstance(entryNode.getValue())) {
                return (T) entryNode.getValue();
            } else {
                if (entryNode.getValue() instanceof JSONObject) {
                    result = getValue(payloadJson.getJSONObject(innerKey), key, clazz);
                    if (result != null) {
                        return result;
                    }
                } else if (entryNode.getValue() instanceof JSONArray) {
                    JSONArray jarray = (JSONArray) entryNode.getValue();
                    for (Object jo : jarray) {
                        result = getValue((JSONObject) jo, key, clazz);
                        if (result != null) {
                            return result;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    /**
     * <pre>
     * 将对象转为json,下划线输出
     * 时间格式按照 2012-01-01 12:12:12<>
     * fastjson缺省使用CamelCase，在1.2.15版本之后，fastjson支持配置PropertyNamingStrategy
     * </pre>
     *
     * @param object
     * @return
     */
    public static String toSnakeCaseJSONString(Object object) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;

        return JSON.toJSONString(object, config, FEATURES);
    }
    
    /**
	 * 获取Json报文中指定节点的字符串形式表示的值
	 * @param contentJson JSON文档对象
	 * @param path 节点Path
	 * @return 节点对应的值的字符串
	 */
	public static String getJsonNodeValueStr(String contentJson, String path)
	{
		Object  valueObj = JSONPath.read(contentJson, path);
		return String.valueOf(valueObj);		
	}
	
	   /**
		 * 获取Json报文中指定节点的字符串形式表示的值
		 * @param contentJson JSON文档对象
		 * @param path 节点Path
		 * @return 节点对应的值的字符串
		 */
		public static List<Object> getJsonNodeValueArray(String contentJson, String path)
		{
            return (List<Object>)JSONPath.read(contentJson, path);
		}
}
