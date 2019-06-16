package com.zzboot.util.web;

import com.zzboot.util.base.data.StringUtil;
import com.zzboot.util.config.Global;
import com.zzboot.util.http.base.HttpUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取地址类
 *
 * @author Admin
 */
@Slf4j
public class AddressRemoteUtils {

    private AddressRemoteUtils(){
        throw new IllegalStateException("Utility class");
    }

    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php" ;

    public static String getRealAddressByIP(String ip) {
        String address = "未知" ;

        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP" ;
        }
        if (Global.getSystemConfig().isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);

            log.info("ip is "+ip + " , address :"+ rspStr);
            if (StringUtil.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}" , ip);
                return address;
            }
            JSONObject obj;
            try {
                obj = JSON.unmarshal(rspStr, JSONObject.class);
                JSONObject data = obj.getObj("data");
                String region = data.getStr("region");
                String city = data.getStr("city");
                address = region + " " + city;
            } catch (Exception e) {
                log.error("获取地理位置异常 {}" , ip);
            }
        }
        return address;
    }
}
