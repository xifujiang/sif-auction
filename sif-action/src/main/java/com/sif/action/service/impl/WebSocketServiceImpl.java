package com.sif.action.service.impl;

import com.sif.action.entity.MessageTb;
import com.sif.action.entity.UserTb;
import com.sif.action.mapper.MessageTbMapper;
import com.sif.action.mapper.UserTbMapper;
import com.sif.action.server.LogWebSocket;
import com.sif.action.service.WebSocketService;
import com.sif.common.entity.email.EmailModel;
import com.sif.common.util.MailSendUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-21 16:49
 **/
@Service
public class WebSocketServiceImpl implements WebSocketService {
    @Resource
    LogWebSocket logWebSocket;

    @Autowired
    MessageTbMapper messageTbMapper;

    @Autowired
    UserTbMapper userTbMapper;

    @Autowired
    MailSendUtils mailSendUtils;

    @Override
    public void sendToUserByUid(String uid, String message) throws IOException {
        MessageTb messageTb = new MessageTb();
        messageTb.setUid(uid);
        messageTb.setMessage(message);
        messageTb.setTime(new Date());
        /*插入信息数据*/
        messageTbMapper.insert(messageTb);
//        logWebSocket.sendMessageTo(uid,message);

        /*发送邮箱*/
        UserTb userTb = userTbMapper.selectById(uid);
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("sif竞拍平台");
        emailModel.setRecieverName(userTb.getName());
        emailModel.setEmailContent(message);
        emailModel.setRecieverEmailAddress(userTb.getMail());
        mailSendUtils.sendEmailAsSysExceptionHtml(emailModel);

    }
}
