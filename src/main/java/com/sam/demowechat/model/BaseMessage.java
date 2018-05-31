package com.sam.demowechat.model;

/**
 * 微信消息-抽象
 * ToUserName	开发者微信号
 * FromUserName	发送方帐号（一个OpenID）
 * CreateTime	消息创建时间 （整型）
 * MsgType	text
 * MsgId	消息id，64位整型
 */
public abstract class BaseMessage {

    String toUserName;
    String fromUserName;
    String createTime;
    String msgType;
    String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        String resStr = "\nToUserName:" + toUserName + "\n";
        resStr+="FromUserName:" + fromUserName + "\n";
        resStr+="CreateTime:" + createTime + "\n";
        resStr+="MsgType:" + msgType + "\n";
        resStr+="MsgId:" + msgId + "\n";
        return resStr;
    }
}
