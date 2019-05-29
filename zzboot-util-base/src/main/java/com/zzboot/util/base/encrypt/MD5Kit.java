package com.zzboot.util.base.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5算法工具
 */
public class MD5Kit {

    private static Logger logger = LoggerFactory.getLogger (MD5Kit.class);
    static MessageDigest md     = null;

    static {
        try {
            md = MessageDigest.getInstance ("MD5");
        } catch (NoSuchAlgorithmException ne) {
            logger.error ("NoSuchAlgorithmException: md5", ne);
        }
    }

    /**
     * 对一个文件求他的md5值
     * 
     * @param f
     *            要求md5值的文件
     * @return md5串
     */
    private static String md5(File f){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read (buffer)) != -1) {
                md.update (buffer, 0, length);
            }

            return new String(Hex.encodeHex (md.digest ()));
        } catch (FileNotFoundException e) {
            logger.error ("md5 file " + f.getAbsolutePath () + " failed:" + e.getMessage ());
            return null;
        } catch (IOException e) {
            logger.error ("md5 file " + f.getAbsolutePath () + " failed:" + e.getMessage ());
            return null;
        } finally {
            try {
                if (fis != null) fis.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    /** 取文件 的md5 */
    public static String md5File(String path){
        File file = new File(path);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (file.isFile () && file.exists ()) return md5 (file);
        else {
            throw new RuntimeException("File[" + path + "] does not exist.");
        }
    }

    /**
     * 求一个字符串的md5值
     * 
     * @param target
     *            字符串
     * @return md5 value
     */
    public static String md5(String target){
        return DigestUtils.md5Hex (target);
    }

    public static void main(String[] args){
        System.out.println (md5 (new File("G:\\PD镜像\\ISO\\Redhat_6.4_iso\\RHEL6.4-20130130.0-Server-ppc64-DVD1.iso")));
    }

}