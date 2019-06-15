
package com.zzboot.util.spring;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Spring 国际化处理 工具类
 * @author Administrator
 */
public class MessageUtils {

    private static MessageSource messageSource  = SpringUtil.getBean(MessageSource.class);

    /**
     * 根据消息键和参数 获取消息
     * 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtil.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, null);
    }


    /**
     * 根据消息键和参数 获取消息
     * 委托给spring messageSource
     *
     * @param locale 语言
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(Locale locale , String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtil.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, locale);
    }


}
