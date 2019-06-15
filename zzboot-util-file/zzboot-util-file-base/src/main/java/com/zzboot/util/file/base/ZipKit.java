package com.zzboot.util.file.base;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;

/**
 * @author Administrator
 * @Description:解压缩工具
 */
public class ZipKit {

    private ZipKit() {}

    private static byte[] buf = new byte[512];

    /**
     * @Title: doZip
     * @Description: 压缩文件
     * @param compressedFilePath
     *            待压缩文件夹
     * @param zipFileRootPath
     *            压缩后文件路径
     * @return
     * @throws IOException
     */
    public static boolean doZip(String compressedFilePath, String zipFileRootPath) throws IOException {
        return doZip (compressedFilePath, zipFileRootPath, "");
    }

    /**
     * @Title: doZip
     * @Description: 压缩文件
     * @param compressedFilePath
     *            待压缩文件夹
     * @param zipFileRootPath
     *            压缩后文件路径
     * @param zipFileName
     *            压缩后文件名
     * @return
     */
    public static boolean doZip(String compressedFilePath, String zipFileRootPath, String zipFileName){
        ZipOutputStream zipOutputStream = null;
        try {
            FileKit.separatorReplace (compressedFilePath);
            FileKit.separatorReplace (zipFileRootPath);
            File compressedFile = new File(compressedFilePath);
            if ("".equalsIgnoreCase (zipFileName)) {
                zipFileName = compressedFile.getName ();
            }
            if (!zipFileRootPath.endsWith (File.separator)) {
                zipFileRootPath = zipFileRootPath + File.separator;
            }
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileRootPath + zipFileName));
            zipOutputStream.setEncoding ("gb2312");
            String base = "";
            return ZipKit.compressFloderChangeToZip (compressedFile, zipOutputStream, base);
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        } finally {
            try {
                if (null != zipOutputStream) zipOutputStream.close ();
            } catch (Exception e2) {
                e2.printStackTrace ();
            }
        }

    }

    private static boolean compressFloderChangeToZip(File compressedFile, ZipOutputStream zipOutputStream, String base){
        FileInputStream fileInputStream = null;
        try {
            if (compressedFile.isDirectory ()) {
                File[] childrenCompressedFileList = compressedFile.listFiles ();
                base = base.length () == 0 ? "" : base + File.separator;
                for ( int i = 0 ; i < childrenCompressedFileList.length ; i++ ) {
                    ZipKit.compressFloderChangeToZip (childrenCompressedFileList[i], zipOutputStream, base + childrenCompressedFileList[i].getName ());
                }
            } else {
                if ("".equalsIgnoreCase (base)) {
                    base = compressedFile.getName ();
                }
                zipOutputStream.putNextEntry (new ZipEntry(base));
                fileInputStream = new FileInputStream(compressedFile);
                int b;
                while ((b = fileInputStream.read (buf)) > 0) {
                    zipOutputStream.write (buf, 0, b);
                }
            }
            return true;
        } catch (Exception e) {
            e.getStackTrace ();
            return false;
        } finally {
            try {
                if (null != fileInputStream) fileInputStream.close ();
            } catch (Exception e2) {
                e2.printStackTrace ();
            }
        }
    }

    /**
     * @Title: unZip
     * @Description: 解压
     * @param zipfile
     * @param destDir
     */
    @SuppressWarnings("rawtypes")
    public static boolean unZip(String zipfile, String destDir){
        destDir = destDir.endsWith ("//") ? destDir : destDir + "//";
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries ();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements ()) {
                zipEntry = (ZipEntry) enumeration.nextElement ();
                File loadFile = new File(destDir + zipEntry.getName ());
                if (zipEntry.isDirectory ()) {
                    loadFile.mkdirs ();
                } else {
                    if (!loadFile.getParentFile ().exists ()) loadFile.getParentFile ().mkdirs ();
                    outputStream = new FileOutputStream(loadFile);
                    inputStream = zipFile.getInputStream (zipEntry);
                    while ((length = inputStream.read (b)) != -1) {
                        outputStream.write (b, 0, length);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
            return false;
        } finally {
            try {
                if (null != zipFile) zipFile.close ();
                if (null != outputStream) outputStream.close ();
                if (null != inputStream) inputStream.close ();
            } catch (Exception e2) {
                e2.printStackTrace ();
            }
        }
    }

    /**
     * @Title: isExistFile
     * @Description: 检查zip中某个文件是否存在并返回路径
     * @param zipfile
     * @param fileName
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String isExistFile(String zipfile, String fileName){
        ZipFile zipFile = null;
        fileName = null == fileName || "".equals (fileName) ? "profile.xml" : fileName;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries ();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements ()) {
                zipEntry = (ZipEntry) enumeration.nextElement ();
                String _fileName = zipEntry.getName ();
                String __fileName = _fileName.substring (_fileName.lastIndexOf ("/") + 1);
                if (__fileName.equals (fileName)) return _fileName;
            }
        } catch (Exception e) {
            e.printStackTrace ();
            return "";
        } finally {
            try {
                if (null != zipFile) zipFile.close ();
            } catch (Exception e2) {
                e2.printStackTrace ();
            }
        }
        return "";
    }

    /**
     * @Title: readFile
     * @Description: 读取压缩包某个文件
     * @param zipfile
     * @param fileName
     *            默认profile.xml
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String readFile(String zipfile, String fileName){
        ZipFile zipFile = null;
        fileName = null == fileName || "".equals (fileName) ? "profile.xml" : fileName;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries ();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements ()) {
                zipEntry = (ZipEntry) enumeration.nextElement ();
                String _fileName = zipEntry.getName ();
                String __fileName = _fileName.substring (_fileName.lastIndexOf ("/") + 1);
                if (__fileName.equals (fileName)) { return FileKit.getString4File (zipFile.getInputStream (zipEntry), "utf-8"); }
            }
        } catch (Exception e) {
            e.printStackTrace ();
            return "";
        } finally {
            try {
                if (null != zipFile) zipFile.close ();
            } catch (Exception e2) {
                e2.printStackTrace ();
            }
        }
        return "";
    }

    public static void main(String[] args){
        // ZipKit.doZip ("G:\\cpuz_64", "G:", "cpuz_64.zip");
        ZipKit.unZip ("F:\\百度云\\迅雷下载\\adt-bundle-windows-x86-20130917.zip", "f:/dd/");
        // System.out.println (ZipKit.isExistFile ("f:\\aa.zip", ""));
    }
}
