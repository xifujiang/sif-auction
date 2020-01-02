package com.sif.common.entity.email;

import java.io.Serializable;

public class EmailModel implements Serializable {

    /**
     * 收件人姓名
     **/
    private String recieverName;

    /**
     * 收件人邮箱地址
     **/
    private String recieverEmailAddress;

    /**
     * 邮件主题
     **/
    private String emailTheme;

    /**
     * 邮件内容
     **/
    private String emailContent;

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverEmailAddress() {
        return recieverEmailAddress;
    }

    public void setRecieverEmailAddress(String recieverEmailAddress) {
        this.recieverEmailAddress = recieverEmailAddress;
    }

    public String getEmailTheme() {
        return emailTheme;
    }

    public void setEmailTheme(String emailTheme) {
        this.emailTheme = emailTheme;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}