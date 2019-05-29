package com.zzboot.util.base.encrypt;

import com.zz.bms.util.base.data.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class JCryKit {

    static {
        //java.security.Security.addProvider (new BouncyCastleProvider ());

    }

    /**
     * Generates the Keypair with the given keyLength.
     * 
     * @param keyLength
     *            length of key
     * @return KeyPair object
     * @throws RuntimeException
     *             if the RSA algorithm not supported
     */
    public static synchronized KeyPair generateKeypair(int keyLength){
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance ("RSA");
            kpg.initialize (keyLength);
            KeyPair keyPair = kpg.generateKeyPair ();
            /*
             * KeyFactory keyFac = KeyFactory.getInstance("RSA"); try { RSAPrivateCrtKeySpec pkSpec = (RSAPrivateCrtKeySpec) keyFac .getKeySpec(keyPair.getPrivate(), RSAPrivateCrtKeySpec.class);
             * System.out.println("Prime exponent p : " + byteArrayToHexString(pkSpec.getPrimeExponentP().toByteArray())); System.out.println("Prime exponent q : " +
             * byteArrayToHexString(pkSpec.getPrimeExponentQ().toByteArray())); System.out.println("Modulus : " + byteArrayToHexString(pkSpec.getModulus().toByteArray()));
             * System.out.println("Private exponent : " + byteArrayToHexString(pkSpec.getPrivateExponent().toByteArray())); System.out.println("Public exponent : " +
             * byteArrayToHexString(pkSpec.getPublicExponent().toByteArray())); } catch (InvalidKeySpecException e) { // TODO Auto-generated catch block e.printStackTrace(); }
             */

            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
    }

    /**
     * 仅页面解密时用
     * 
     * @param encrypted
     *            full encrypted text
     * @param keys
     *            RSA keys
     * @return decrypted text
     * @throws RuntimeException
     *             if the RSA algorithm not supported or decrypt operation failed
     */
    public static String decrypt(String encrypted, KeyPair keys){
        if (StringUtils.isBlank (encrypted) || null == keys) { return null; }
        Cipher dec;
        try {
            dec = Cipher.getInstance ("RSA");
            dec.init (Cipher.DECRYPT_MODE, keys.getPrivate ());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
        String[] blocks = encrypted.split ("\\s");
        StringBuffer result = new StringBuffer();
        try {
            for ( int i = blocks.length - 1 ; i >= 0 ; i-- ) {
                byte[] data = hexStringToByteArray (blocks[i]);
                byte[] decryptedBlock = dec.doFinal (data);
                result.append (new String(decryptedBlock,"utf-8"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Decrypt error",e);
        }
        /**
         * Some code is getting added in first 2 digits with Jcryption need to investigate
         */
        return result.reverse ().toString ();
    }

    /**
     * 公钥加密,
     * 
     * @param data
     * @param keys
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, KeyPair keys) throws Exception {
        return encrypt (data, keys.getPublic ());
    }

    public static String encrypt(String data, String publicKey, String modulus) throws Exception {
        return encrypt (data, getPublicKey (publicKey, modulus));
    }

    /**
     * 使用模和指数生成RSA公钥 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA /None/NoPadding】
     * 
     * @param modulus
     *            模
     * @param exponent
     *            指数
     * @return
     */
    public static RSAPublicKey getPublicKey(String modulus, String exponent){
        try {
            BigInteger b1 = new BigInteger(modulus,16);
            BigInteger b2 = new BigInteger(exponent,16);
            KeyFactory keyFactory = KeyFactory.getInstance ("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1,b2);
            return (RSAPublicKey) keyFactory.generatePublic (keySpec);
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }

    public static String encrypt(String data, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance ("RSA");
        cipher.init (Cipher.ENCRYPT_MODE, key);
        // 模长
        int key_len = ((RSAPublicKey) key).getModulus ().bitLength () / 8;
        // 加密数据长度 <= 模长-11
        String[] datas = splitString (data, key_len - 11);
        String mi = "";
        // 如果明文长度大于模长-11则要分组加密
        for ( String s : datas ) {
            mi += byteArrayToHexString (cipher.doFinal (s.getBytes ()));
        }
        return mi;
    }

    /**
     * 加密Key的长度等于1024或者2048
     */
    public static int KEYSIZE   = 1024;
    /**
     * 解密时必须按照此分组解密
     */
    public static int decodeLen = KEYSIZE / 8;

    /**
     * 解密 private key 方法说明。
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static String decryptPrivateKey(String data, PrivateKey privateKey) throws Exception {
        byte[] buffers = new byte[] {};
        byte[] encode = hexStringToByteArray (data);
        for ( int i = 0 ; i < encode.length ; i += decodeLen ) {
            byte[] subarray = ArrayUtils.subarray (encode, i, i + decodeLen);
            byte[] doFinal = decryptByPrivateKey (subarray, privateKey);
            buffers = ArrayUtils.addAll (buffers, doFinal);
        }
        return new String(buffers,"UTF-8");
    }

    /**
     * 用私钥解密
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     * @author East271536394
     */
    private static byte[] decryptByPrivateKey(byte[] data,PrivateKey privateKey) throws Exception {
        // 对数据解密
        Cipher cipher = Cipher.getInstance ("RSA");
        cipher.init (Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal (data);
    }

    // /**
    // * 私钥解密
    // *
    // * @param data
    // * @param privateKey
    // * @return
    // * @throws Exception
    // */
    // public static String decryptByPrivateKey(String data,PrivateKey privateKey) throws Exception{
    // Cipher cipher = Cipher.getInstance ("RSA");
    // cipher.init (Cipher.DECRYPT_MODE, privateKey);
    // // 模长
    // int key_len = ((RSAPrivateKey) privateKey).getModulus ().bitLength () / 8;
    // byte[] bytes = parseHexStr2Byte (data);
    // byte[] bcd = ASCII_To_BCD (bytes, bytes.length);
    // // 如果密文长度大于模长则要分组解密
    // String ming = "";
    // byte[][] arrays = splitArray (bcd, key_len);
    // for ( byte[] arr : arrays ) {
    // ming += new String (cipher.doFinal (arr));
    // }
    // return ming;
    // }

    /**
     * ASCII码转BCD码
     */
    public static byte[] ASCII_To_BCD(byte[] ascii,int asc_len){
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for ( int i = 0 ; i < (asc_len + 1) / 2 ; i++ ) {
            bcd[i] = asc_to_bcd (ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd (ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    public static byte asc_to_bcd(byte asc){
        byte bcd;

        if ((asc >= '0') && (asc <= '9')) {
            bcd = (byte) (asc - '0');
        }else if ((asc >= 'A') && (asc <= 'F')) {
            bcd = (byte) (asc - 'A' + 10);
        }
        else if ((asc >= 'a') && (asc <= 'f')) {
            bcd = (byte) (asc - 'a' + 10);
        }
        else {
            bcd = (byte) (asc - 48);
        }
        return bcd;
    }

    /**
     * BCD转字符串
     */
    public static String bcd2Str(byte[] bytes){
        char temp[] = new char[bytes.length * 2], val;

        for ( int i = 0 ; i < bytes.length ; i++ ) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * 拆分字符串
     */
    public static String[] splitString(String string, int len){
        int x = string.length () / len;
        int y = string.length () % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for ( int i = 0 ; i < x + z ; i++ ) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring (i * len, i * len + y);
            } else {
                str = string.substring (i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }

    /**
     * 拆分数组
     */
    public static byte[][] splitArray(byte[] data,int len){
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        byte[][] arrays = new byte[x + z][];
        byte[] arr;
        for ( int i = 0 ; i < x + z ; i++ ) {
            arr = new byte[len];
            if (i == x + z - 1 && y != 0) {
                System.arraycopy (data, i * len, arr, 0, y);
            } else {
                System.arraycopy (data, i * len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    /**
     * Parse url string (Todo - better parsing algorithm)
     * 
     * @param url
     *            value to parse
     * @param encoding
     *            encoding value
     * @return Map with param name, value pairs
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map parse(String url, String encoding){
        try {
            String urlToParse = URLDecoder.decode (url, encoding);
            String[] params = urlToParse.split ("&");
            Map parsed = new HashMap();
            for ( int i = 0 ; i < params.length ; i++ ) {
                String[] p = params[i].split ("=");
                String name = p[0];
                String value = (p.length == 2) ? p[1] : null;
                parsed.put (name, value);
            }
            return parsed;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown encoding.",e);
        }
    }

    /**
     * Return public RSA key modulus
     * 
     * @param keyPair
     *            RSA keys
     * @return modulus value as hex string
     */
    public static String getPublicKeyModulus(KeyPair keyPair){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic ();
        return publicKey.getModulus ().toString (16);
    }

    /**
     * Return public RSA key exponent
     * 
     * @param keyPair
     *            RSA keys
     * @return public exponent value as hex string
     */
    public static String getPublicKeyExponent(KeyPair keyPair){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic ();
        return publicKey.getPublicExponent ().toString (16);
    }

    /**
     * Max block size with given key length
     * 
     * @param keyLength
     *            length of key
     * @return numeber of digits
     */
    public static int getMaxDigits(int keyLength){
        return ((keyLength * 2) / 16) + 3;
    }

    /**
     * Convert byte array to hex string
     * 
     * @param bytes
     *            input byte array
     * @return Hex string representation
     */
    private static String byteArrayToHexString(byte[] bytes){
        StringBuffer result = new StringBuffer();
        for ( int i = 0 ; i < bytes.length ; i++ ) {
            result.append (Integer.toString ((bytes[i] & 0xff) + 0x100, 16).substring (1));
        }
        return result.toString ();
    }

    /**
     * Convert hex string to byte array 十六进制字符串转字节数组
     * 
     * @param data
     *            input string data
     * @return bytes
     */
    private static byte[] hexStringToByteArray(String data){
        int k = 0;
        byte[] results = new byte[data.length () / 2];
        for ( int i = 0 ; i < data.length () ; ) {
            results[k] = (byte) (Character.digit (data.charAt (i++), 16) << 4);
            results[k] += (byte) (Character.digit (data.charAt (i++), 16));
            k++;
        }
        return results;
    }

    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]){
        StringBuffer sb = new StringBuffer();
        for ( int i = 0 ; i < buf.length ; i++ ) {
            String hex = Integer.toHexString (buf[i] & 0xFF);
            if (hex.length () == 1) {
                hex = '0' + hex;
            }
            sb.append (hex.toUpperCase ());
        }
        return sb.toString ();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr){
        if (hexStr.length () < 1) return null;
        byte[] result = new byte[hexStr.length () / 2];
        for ( int i = 0 ; i < hexStr.length () / 2 ; i++ ) {
            int high = Integer.parseInt (hexStr.substring (i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt (hexStr.substring (i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args){
        KeyPair kp = JCryKit.generateKeypair (1024);
        try {
            System.out.println (Base64.encodeBytes ("root".getBytes ()));
            System.out.println (new String(Base64.decode (Base64.encodeBytes ("root".getBytes ()))));
            System.out.println ("加密前：abcde");
            String aa = JCryKit.encrypt ("abcde", kp.getPublic ());
            System.out.println ("加密后：" + aa);
            String data = JCryKit.decryptPrivateKey (aa, kp.getPrivate ());
            System.out.println ("解密后：" + data);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}