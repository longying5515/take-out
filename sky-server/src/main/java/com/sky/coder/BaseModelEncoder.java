package com.sky.coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sky.entity.BaseResponseMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author YUSHENGDADA
 * @title: BaseModelEncoder
 * @projectName v2_lab
 * @description: 实体编码器
 * @date 2022/8/22 0022下午 14:15
 */
public class BaseModelEncoder  implements Encoder.Text<BaseResponseMessage> {
    @Override
    public String encode(BaseResponseMessage baseResponseMessage) throws EncodeException {
        try {
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.writeValueAsString(baseResponseMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
