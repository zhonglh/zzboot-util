package com.zzboot.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 */
public class PropertyConfig {



    public Logger logger = LoggerFactory.getLogger (PropertyConfig.class);

    public Properties prop   = new Properties();


    public PropertyConfig(String filePath){
        init(filePath);
    }

    public void init(String filePath){
        InputStream is = null;
        try {
            ClassPathResource cpr = new ClassPathResource(filePath);
            is = cpr.getInputStream ();
            prop.load (is);
        } catch (IOException e) {
            logger.error ("-------------> Load Properties " + filePath + " failer!");
        } finally {
            if (null != is) {
                try {
                    is.close ();
                } catch (IOException e) {
                    logger.error ("Close inputStream failer");
                }
            }
        }
    }

    public Properties getProperties(String filePath){
        InputStream is = null;
        Properties p = new Properties();
        try {
            ClassPathResource cpr = new ClassPathResource(filePath);
            is = cpr.getInputStream ();
            p.load (is);
            return p;
        } catch (IOException e) {
            logger.error ("-------------> Load Properties " + filePath + " failer!");
        } finally {
            if (null != is) {
                try {
                    is.close ();
                } catch (IOException e) {
                    logger.error ("Close inputStream failer");
                }
            }
        }
        return p;
    }

    /**
     * 由KEY返回VALUE，如果VALUE为空，则返回DEFAULTVALUE
     *
     * @param key
     * @param defaultValue
     * @return String
     */
    public String getProperty(String key, String defaultValue){
        return prop.getProperty (key, defaultValue);
    }



}
