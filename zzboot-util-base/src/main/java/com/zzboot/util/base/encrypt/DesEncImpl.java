package com.zzboot.util.base.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DesEncImpl {
	

    private static String PASSWORD_CRYPT_KEY = "FBL_*5Plg;Eb_";
    private final static String DES = "DES";

    /**
     * DES加密算法
     * 
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);
    }

    /**
     * DES解密算法
     * 
     * @param src
     * @param key
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);
    }

    /**
     * 对数据进行解密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 对数据进行加密
     * 
     * @param password
     * @return
     * @throws Exception
     */
    public static String encrypt(String password) {
        try {
            return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY
                    .getBytes()));
        } catch (Exception e) {
        }
        return null;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("decrypt error!");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    public static void main(String[] args) {
    	//解密

        System.out.println(encrypt("123456"));
    	System.out.println(decrypt("AEFAD7A2C2A2FCFC"));
	}

	
}
