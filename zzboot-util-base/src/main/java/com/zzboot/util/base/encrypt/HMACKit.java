package com.zzboot.util.base.encrypt;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class HMACKit {




    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }


    /**
     * HMAC加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */

    public static String hmac(String message, String secret ) {
        return hmac(message,  secret , "HmacSHA256");
    }
    public static String hmac(String message, String secret ,String algorithm) {
        String hash = "";
        try {
            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), algorithm);
            mac.init(secret_key);
            byte[] bytes = mac.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error hmac ===========" + e.getMessage());
        }
        return hash;
    }

    public static void main(String[] args) {
        String message = "data=0eUw2Nk2isX+IRlttM7eKomZCAfJlocPw2lG4uVrc0rOqzK85qW9tKshFrFmY8mrmD34UO6UBRpOaG4dKnENniZT3l0KSg/DFOiUu+YbJdxbpPiJs77WYIO1EZ5A70Wxxkc7u3rR2SQfqMwmgP/0yEPeqMqYMcDNgy/CUI5sBNagdue0L/UknhYSzbzxCNlyA3pyy84IETNfQmiQLHbkLwD3TvfT/rZdYu9PV2QpNGEFxe6PjcdFFU8gbIeuImjZyJIAU8umpMiMpx3xNqnraie8e7+5dzrS&mess=12313&timestamp=123457&key=78f9b4fad3481fbce1df0b30eee58577";
        String secret = "78f9b4fad3481fbce1df0b30eee58577";
        System.out.println(hmac(message , secret));
    }

}
