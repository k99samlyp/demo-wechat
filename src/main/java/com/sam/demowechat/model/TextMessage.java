package com.sam.demowechat.model;

/**
 * ToUserName	开发者微信号
 * FromUserName	发送方帐号（一个OpenID）
 * CreateTime	消息创建时间 （整型）
 * MsgType	text
 * Content	文本消息内容
 * MsgId	消息id，64位整型
 */
public class TextMessage extends BaseMessage {

    String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Content:" + content + "\n";
    }
}
