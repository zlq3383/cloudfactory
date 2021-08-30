package com.neuedu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class FactoryResult {
    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status;

    private String msg;

    private Object data;

    public FactoryResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public FactoryResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static FactoryResult build(Integer status, String msg, Object data) {
        return new FactoryResult(status, msg, data);
    }

    public static FactoryResult ok(Object data) {
        return new FactoryResult(data);
    }

    public static FactoryResult ok() {
        return new FactoryResult(null);
    }
    public static  FactoryResult fail(){
        return new FactoryResult(403,"拒绝此请求",null);
    }

    /**
     * 把json转化为FactoryResult
     * @param jsonData
     * @param clazz
     * @return
     */
    public static FactoryResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, FactoryResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }

            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * @param json
     * @return
     */
    public static FactoryResult format(String json){
        try{
            return MAPPER.readValue(json,FactoryResult.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * objec是集合转化
     * clazz是集合中的类型
     * @param jsonData
     * @param clazz
     * @return
     */
    public static FactoryResult formatToList(String jsonData,Class<?> clazz){
        try{
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            JsonNode data=jsonNode.get("data");
            Object obj=null;
            if(data.isArray()&&data.size()>0){
                obj=MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }catch (Exception e){
            return null;
        }
    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

