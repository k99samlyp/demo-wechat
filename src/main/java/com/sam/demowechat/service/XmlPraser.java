package com.sam.demowechat.service;

import com.sam.demowechat.model.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;

import javax.xml.stream.util.XMLEventConsumer;

/**
 * XML解析器
 */
public class XmlPraser {


    public static String getMsgType(String xmlStr) throws Exception{
        Document document = DocumentHelper.parseText(xmlStr);
        Element element = document.getRootElement();
        return element.elementText("MsgType");
    }

    public static TextMessage praseTextMessage(String xmlStr) throws Exception{
        TextMessage _textM = new TextMessage();
        Document document = DocumentHelper.parseText(xmlStr);
        Element element = document.getRootElement();
        _textM.setToUserName(element.elementText("ToUserName"));
        _textM.setFromUserName(element.elementText("FromUserName"));
        _textM.setCreateTime(element.elementText("CreateTime"));
        _textM.setMsgType(element.elementText("MsgType"));
        _textM.setContent(element.elementText("Content"));
        _textM.setMsgId(element.elementText("MsgId"));
        return _textM;
    }

    public static String textMessageAsXml(TextMessage textMessage) throws Exception{

        Document _repText = DocumentHelper.createDocument();

        Element _root = new DOMElement("xml");
        Element _ToUserName = new DOMElement("ToUserName");
        _ToUserName.add(new DOMCDATA(textMessage.getToUserName()));
        Element _FromUserName = new DOMElement("FromUserName");
        _FromUserName.add(new DOMCDATA(textMessage.getFromUserName()));
        Element _CreateTime = new DOMElement("CreateTime");
        _CreateTime.add(new DOMText(textMessage.getCreateTime()));
        Element _MsgType = new DOMElement("MsgType");
        _MsgType.add(new DOMCDATA(textMessage.getMsgType()));
        Element _Content = new DOMElement("Content");
        _Content.add(new DOMCDATA(textMessage.getContent()));
        _root.add(_ToUserName);
        _root.add(_FromUserName);
        _root.add(_CreateTime);
        _root.add(_MsgType);
        _root.add(_Content);

        _repText.setRootElement(_root);

        //System.out.println("aaa:" + _repText.asXML());

        return _repText.asXML();
    }
}
