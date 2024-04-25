package com.sky.service;

import com.alibaba.fastjson.JSONObject;
import com.sky.coder.BaseModelEncoder;
import com.sky.coder.HashMapEncoder;
import com.sky.entity.UserMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.sky.entity.BaseResponseMessage;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@ServerEndpoint(value = "/api/websocket/client/{clientId}",encoders = {HashMapEncoder.class, BaseModelEncoder.class})

public class WebSocketClient {

    public static HashMap<String,WebSocketClient> webSocketClientMap = new HashMap<>();

    public static ConcurrentHashMap<String, List<UserMessageModel>> ToBeSentMap = new ConcurrentHashMap<>();

    public static AtomicInteger onlineUsers = new AtomicInteger();

    private Session infoSession;

    @OnOpen
    public void onOpen(Session session,@PathParam("clientId") String clientId){
        if (!webSocketClientMap.containsKey(clientId)){
            onlineUsers.addAndGet(1);
        }
        webSocketClientMap.put(clientId,this);
        infoSession = session;
        log.info("客户端:{}建立连接，当前在线人数:{}",clientId,onlineUsers.get());

        /**
         * 消息补偿
         */
        if (!CollectionUtils.isEmpty(this.ToBeSentMap.get(clientId))){
            this.ToBeSentMap.get(clientId).forEach(userMessageModel->{
                this.sendMessage(BaseResponseMessage.success(userMessageModel));
            });
        }
    }

    @OnClose
    public void onclose(Session session, @PathParam("clientId") String clientId){
        if (webSocketClientMap.containsKey(clientId)) {
            webSocketClientMap.remove(clientId);
            onlineUsers.getAndAdd(-1);
        }
        log.info("客户端:{}断开连接，当前在线人数:{}",clientId,onlineUsers.get());
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("连接异常:{}",error.getMessage());
    }

    @OnMessage
    public void onMessage(String message, Session session,@PathParam("clientId") String clientId){
        UserMessageModel userMessageModel = JSONObject.parseObject(message, UserMessageModel.class);
        if (userMessageModel == null){
            this.sendMessage(BaseResponseMessage.error(null,"传递参数结构异常"));
        }
        if(!webSocketClientMap.containsKey(userMessageModel.getAcceptId())){
            // 放到待发送列表里
            if(!ToBeSentMap.containsKey(userMessageModel.getAcceptId())){
                ToBeSentMap.put(userMessageModel.getAcceptId(),new CopyOnWriteArrayList<>());
            }
            List<UserMessageModel> addList = ToBeSentMap.get(userMessageModel.getAcceptId());
            addList.add(userMessageModel);
            log.info("客户端:{} 发送消息到接受端:{} 不在线，放置到代发送列表，当前待发送列表:{}条",clientId,userMessageModel.getAcceptId(), addList.size());
            this.sendMessage(BaseResponseMessage.error(null,"接收端不在线"));
        }else{
            log.info("客户端:{} 发送到客户端:{},消息内容:{}",clientId,userMessageModel.getAcceptId(),userMessageModel.getMessage());
            webSocketClientMap.get(userMessageModel.getAcceptId()).sendMessage(BaseResponseMessage.success(userMessageModel));
            this.sendMessage(BaseResponseMessage.success(userMessageModel));
        }
    }

    private void  sendMessage(Object message){
        try {
            this.infoSession.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            throw new RuntimeException(e);
        }
    }
}
