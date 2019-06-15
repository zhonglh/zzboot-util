package com.zzboot.util.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author admin
 */
@Slf4j
public class AddressDbUtils {

    private static DbSearcher searcher = null;
    private static  int algorithm = DbSearcher.BTREE_ALGORITHM;

    static{
        try {
            String dbPath = AddressRemoteUtils.class.getResource("/ip2region.db").getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
                dbPath = tmpDir + "ip.db";
                file = new File(dbPath);
                FileUtils.copyInputStreamToFile(AddressRemoteUtils.class.getClassLoader().getResourceAsStream("classpath:ip2region/ip2region.db"), file);



                DbConfig config = new DbConfig();
                DbSearcher searcher = new DbSearcher(config, file.getPath());
            }
        } catch (Exception e) {
            log.error("获取地址信息异常：{}", e);
        }
    }


    public static String getCityInfo(String ip) {

        if(searcher == null){
            return null;
        }

        try {

            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
                default:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock = null;
            if (!Util.isIpAddress(ip)) {
                log.error("Error: Invalid ip address");
                return null;
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            if(dataBlock != null) {
                return dataBlock.getRegion();
            }
        } catch (Exception e) {
            log.error("获取地址信息异常：{}", e);
        }
        return null;
    }

}