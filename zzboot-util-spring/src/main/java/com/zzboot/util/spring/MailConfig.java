package com.zzboot.util.spring;


public class MailConfig {


    public final static PropertyConfig pc = new PropertyConfig("mail.properties");


    public static boolean needSendMail = true ;

    public static String smtpHost = pc.getProperty ("mail.smtpHost", "");

    public static String sender = pc.getProperty ("mail.sender", "");


    public static String senderName = pc.getProperty ("mail.senderName", "");

    public static String user = pc.getProperty ("mail.user", "");

    public static String pwd = pc.getProperty ("mail.pwd", "");


    public static String getSmtpHost() {
        return smtpHost;
    }

    public static void setSmtpHost(String smtpHost) {
        MailConfig.smtpHost = smtpHost;
    }

    public static String getSender() {
        return sender;
    }

    public static void setSender(String sender) {
        MailConfig.sender = sender;
    }

    public static String getSenderName() {
        return senderName;
    }

    public static void setSenderName(String senderName) {
        MailConfig.senderName = senderName;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        MailConfig.user = user;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        MailConfig.pwd = pwd;
    }
}
