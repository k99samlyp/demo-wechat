package com.sam.demowechat.controller;

import com.sam.demowechat.model.TextMessage;
import com.sam.demowechat.service.XmlPraser;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    public static final String token = "374886sam";

    @GetMapping(value = "/")
    @ResponseBody
    /**
     * 设置微信URL时，需要校验签名才能保存成功 (GET方法拦截)
     */
    public String setUrl(@RequestParam("echostr") Object echostr,
    @RequestParam("signature") Object signature,
    @RequestParam("timestamp") Object timestamp,
    @RequestParam("nonce") Object nonce) throws Exception {

       logger.info("signature:" + signature.toString());
       logger.info("timestamp:" + timestamp.toString());
       logger.info("nonce:" + nonce.toString());
       logger.info("echostr:" + echostr.toString());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(token);
        arrayList.add(timestamp.toString());
        arrayList.add(nonce.toString());
        Collections.sort(arrayList);
        String desc = "";
        for (String _str:arrayList){
            desc+=_str;
        }
        arrayList.clear();

        if (signature.equals(new String(DigestUtils.sha1Hex(desc)))){
            return echostr.toString();
        }
        else {
            return signature.toString();
        }
    }


    @PostMapping(value = "/")
    @ResponseBody
    /**
     * 接收消息
     */
    public String recFromTencent(@RequestBody String reqBody) throws Exception {
        logger.info("\n收到原始报文:" + reqBody);
        String msgType = XmlPraser.getMsgType(reqBody);
        String responseStr = "";

        if ("text".equals(msgType)){
            TextMessage _textM = XmlPraser.praseTextMessage(reqBody);

            //收件人发件人翻转
            String from = _textM.getFromUserName();
            String to = _textM.getToUserName();
            _textM.setFromUserName(to);
            _textM.setToUserName(from);

            if (_textM.getContent().indexOf("傻子") > -1){
                _textM.setContent("你才是傻子");
            }
            else {
                _textM.setContent("杨泽雪是傻子！");
            }


            responseStr = XmlPraser.textMessageAsXml(_textM);
            //logger.info(_textM.toString());
        }
        else if ("image".equals(msgType)){

        }

        return responseStr;

    }



//    @RequestParam("ToUserName") Object ToUserName,
//    @RequestParam("FromUserName") Object FromUserName,
//    @RequestParam("CreateTime") Object CreateTime,
//    @RequestParam("MsgType") Object MsgType,
//    @RequestParam("Content") Object Content,
//    @RequestParam("MsgId") Object MsgId
}
