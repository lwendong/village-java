package com.village.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.village.constant.WebSocketType;
import com.village.entity.User;
import com.village.entity.Ws;
import com.village.entity.vo.WsVo;
import com.village.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@ServerEndpoint(value = "/webSocket/{userId}")
@Component
public class WebSocket {


    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        WebSocket.userService = userService;
    }

    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Ws> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            this.session = session;
            this.userId = userId;
            Ws ws = new Ws(userId, this, "","");

            if (webSocketMap.containsKey(userId)) {
                webSocketMap.remove(userId);
                webSocketMap.put(userId, ws);
            } else {
                webSocketMap.put(userId, ws);
                addOnlineCount();
            }
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnlineCount();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if (StringUtils.isNotBlank(message)) {
                JSONObject jsonObject = JSON.parseObject(message);
                String type = jsonObject.get("type").toString();
                if (StringUtils.equals(WebSocketType.LIST.getValue(), type)) {
                    Ws ws = webSocketMap.get(this.userId);
                    ws.setAddress(jsonObject.get("address").toString());
                    initList();
                } else if (StringUtils.equals(WebSocketType.SEND.getValue(), type)) {
                    sendMessageInfo(message);
                }else if (StringUtils.equals(WebSocketType.GROUP.getValue(), type)) {
                    initGroup(message);
                }else if (StringUtils.equals(WebSocketType.GROUP_SEND.getValue(), type)) {
                    sendMessageInfoGroup(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 服务器推送全部人
     */
    public void sendAllMessage(String message) throws IOException {
        for (Ws ws : webSocketMap.values()) {
            ws.getWebSocket().sendMessage(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }


    private void initList() throws IOException {
        List<String> userIds = webSocketMap.values().stream().map(Ws::getUserId).collect(Collectors.toList());
        List<User> userByIds = userService.getUserByIds(userIds);
        List<WsVo> wsVoList = new ArrayList<>();
        for (Ws nextWs : webSocketMap.values()) {
            WsVo wsVo = WsVo.builder().address(nextWs.getAddress()).userId(nextWs.getUserId()).build();
            for (User user : userByIds) {
                if (StringUtils.equals(nextWs.getUserId(), user.getId())) {
                    String nick = user.getNick();
                    wsVo.setNick(nick);
                    if (StringUtils.isBlank(nick)) {
                        wsVo.setNick(user.getUsername());
                    }
                    wsVo.setAvatar(user.getAvatarUrl());
                    nextWs.setAvatar(user.getAvatarUrl());
                    break;
                }
            }
            wsVoList.add(wsVo);
        }
        JSONObject message = new JSONObject();
        message.put("type",WebSocketType.LIST.getValue());
        message.put("message", wsVoList);
        sendAllMessage(message.toString());
    }

    private void initGroup(String message) throws IOException {
        JSONObject jsonObject = JSON.parseObject(message);
        WsVo wsVo = new WsVo();
        wsVo.setNick(jsonObject.get("nick").toString());
        wsVo.setAvatar(null);
        wsVo.setType("group");
        wsVo.setAddress(jsonObject.get("address").toString());
        List<String> groupList = JSONArray.parseArray(jsonObject.get("groupList").toString(), String.class);
        wsVo.setGroupUsers(groupList);
        for (String userId : groupList){
            Ws ws = webSocketMap.get(userId);
            JSONObject json = new JSONObject();
            json.put("type",WebSocketType.GROUP.getValue());
            json.put("message", wsVo);
            ws.getWebSocket().sendMessage(json.toString());
        }
    }
    
    
    /**
     * 发送自定义消息
     */
    private void sendMessageInfo(String message) throws IOException {
        JSONObject messageJson = JSON.parseObject(message);
        String toUserId = messageJson.getString("toUserId");
        Ws toWs = webSocketMap.get(toUserId);
        Ws fromWs = webSocketMap.get(this.userId);
        messageJson.put("avatar",fromWs.getAvatar());
        JSONPath.set(messageJson,"type",WebSocketType.RECEIVE.getValue());
        toWs.getWebSocket().sendMessage(messageJson.toString());
    }

    private void sendMessageInfoGroup(String message) throws IOException {
        JSONObject messageJson = JSON.parseObject(message);
        List<String> toUserIds = JSONArray.parseArray(messageJson.get("toUserId").toString(), String.class);
        Ws fromWs = webSocketMap.get(this.userId);
        messageJson.put("avatar",fromWs.getAvatar());
        JSONPath.set(messageJson,"type",WebSocketType.GROUP_RECEIVE.getValue());
        for(String userId : toUserIds){
            if(!StringUtils.equals(userId,this.userId)){
                Ws toWs = webSocketMap.get(userId);
                toWs.getWebSocket().sendMessage(messageJson.toString());
            }
        }
    }
}
