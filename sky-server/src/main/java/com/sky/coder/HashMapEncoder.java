package com.sky.coder;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;

/**
 * @author YUSHENGDADA
 * @title: ServerEncoder
 * @projectName v2_lab
 * @description: WebSocket编码器
 * @date 2022/8/22 0022上午 11:42
 */
public class HashMapEncoder implements Encoder.Text<HashMap> {
    private static final Logger log = LoggerFactory.getLogger(HashMapEncoder.class);

    /**
     * 这里的参数 hashMap 要和  Encoder.Text<T>保持一致
     * @param hashMap
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(HashMap hashMap) throws EncodeException {
        /*
         * 这里是重点，只需要返回Object序列化后的json字符串就行
         * 你也可以使用gosn，fastJson来序列化。
         * 这里我使用fastjson
         */
        try {
            return JSONObject.toJSONString(hashMap);
        }catch (Exception e){
            log.info("ServerEncoder编码异常:{}",e.getMessage());
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //可忽略
    }

    @Override
    public void destroy() {
        //可忽略
    }
}