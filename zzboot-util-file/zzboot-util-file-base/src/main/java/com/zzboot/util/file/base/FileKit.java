package com.zzboot.util.file.base;

import com.zzboot.util.base.data.Base64;
import com.zzboot.util.base.java.IdUtils;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;

import java.io.*;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件操作工具
 * @author Administrator
 */
public class FileKit {

    /**
     * 生成一个文件路径
     * @param prefix
     * @return
     */
    public static String buildFilePath(String prefix) {
        Date currDate = new Date();
        StringBuilder sb = new StringBuilder();


        if (StringUtils.isNotEmpty(prefix)) {
            sb.append(prefix);
        }


        //生成uuid
        String uuid = IdUtils.getId();
        //文件路径
        sb.append(File.separatorChar).append(DateUtils.format(currDate, "yyyyMMdd"));
        sb.append(File.separatorChar).append(DateUtils.format(currDate, "HHmmssS"));
        sb.append(uuid);

        return sb.toString();

    }

    /**
     * 生成一个文件路径
     * @param prefix    前缀
     * @param suffix    后缀
     * @return
     */
    public static String buildFilePath(String prefix , String suffix) {
        String filePath = buildFilePath(prefix);
        if(StringUtils.isEmpty(suffix)){
            return filePath;
        }else {
            return filePath + "." + suffix;
        }

    }


    /**
     * 获取文件后缀.
     */
    public static String getSuffix(String name) {
        if (name.indexOf(".") == -1) {
            return "";
        }

        String suffix = name.substring(name.lastIndexOf(".") + 1);
        if(suffix == null || suffix.isEmpty())  {
            return "";
        }
        else {
            return suffix.trim().toLowerCase();
        }
    }

    /**
     * 将源文件的数据写入到目标文件中， 不会检查源文件是否存在， 若目标文件存在则直接写入， 否则创建目标文件后再进行写入。
     * 
     * @param srcPath
     * @param desPath
     */
    private static void copyFile(String srcPath, String desPath){
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcPath);
            out = new FileOutputStream(desPath);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read (bt)) > 0) {
                out.write (bt, 0, count);
            }
            in.close ();
            out.close ();
        } catch (IOException ex) {
            ex.printStackTrace ();
        } finally {
            try {
                if (null != in) {
                    in.close ();
                }
                if (null != out) {
                    out.close ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
    
    
	public static void copyFile(InputStream in, File file) throws IOException, FileNotFoundException {
		BufferedInputStream inputStream;
		BufferedOutputStream outputStream;
		inputStream = new BufferedInputStream(in);
		outputStream = new BufferedOutputStream(new FileOutputStream(file));
		Streams.copy(inputStream, outputStream, false);
	}

    /**
     * 复制文件，若文件存在则替换该文件。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void copyAndReplaceFile(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        File srcFile = new File(srcPath);
        if (!srcFile.isFile ()) { throw new Exception("source (" + srcPath + ")file not found!"); }
        copyFile (srcPath, desPath);
    }

    /**
     * 复制文件，若文件已存在则不进行替换。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void copyAndNotReplaceFile(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        File srcFile = new File(srcPath);
        File desFile = new File(desPath);
        if (!srcFile.isFile ()) { throw new Exception("source(" + srcPath + ") file not found!"); }
        if (desFile.isFile ()) { return; }
        copyFile (srcPath, desPath);
    }

    /**
     * 移动文件，若文件存在则替换该文件。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void moveAndReplaceFile(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        copyAndReplaceFile (srcPath, desPath);
        deleteFile (srcPath);
    }

    /**
     * 移动文件，若文件存在则不进行替换。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void moveAndNotReplaceFile(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        copyAndNotReplaceFile (srcPath, desPath);
        deleteFile (srcPath);
    }

    /**
     * 复制并合并文件夹， 不会替换目标文件夹中已经存在的文件或文件夹。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void copyAndMergerFolder(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        File folder = getFolder (srcPath);
        if (null == folder) {
            throw new FileNotFoundException(srcPath + " folder not found");
        }
        createFolder (desPath);
        File[] files = folder.listFiles ();
        for ( File file : files ) {
            String src = file.getAbsolutePath ();
            String des = desPath + File.separator + file.getName ();
            if (file.isFile ()) {
                copyAndNotReplaceFile (src, des);
            } else if (file.isDirectory ()) {
                copyAndMergerFolder (src, des);
            }
        }
    }

    /**
     * 复制并替换文件夹， 将目标文件夹完全替换成源文件夹， 目标文件夹原有的资料会丢失。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void copyAndReplaceFolder(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        File folder = getFolder (srcPath);
        if (null == folder) {
            throw new FileNotFoundException(srcPath + " folder not found");
        }
        createNewFolder (desPath);
        File[] files = folder.listFiles ();
        for ( File file : files ) {
            String src = file.getAbsolutePath ();
            String des = desPath + File.separator + file.getName ();
            if (file.isFile ()) {
                copyAndReplaceFile (src, des);
            } else if (file.isDirectory ()) {
                copyAndReplaceFolder (src, des);
            }
        }
    }

    /**
     * 合并文件夹后，将源文件夹删除。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void moveAndMergerFolder(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        copyAndMergerFolder (srcPath, desPath);
        deleteFolder (srcPath);
    }

    /**
     * 替换文件夹后，将源文件夹删除。
     * 
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void moveAndReplaceFolder(String srcPath, String desPath) throws Exception {
        srcPath = separatorReplace (srcPath);
        desPath = separatorReplace (desPath);
        copyAndReplaceFolder (srcPath, desPath);
        deleteFolder (srcPath);
    }

    /**
     * 创建文件夹，如果文件夹存在则不进行创建。
     * 
     * @param path
     * @throws Exception
     */
    public static void createFolder(String path) throws Exception {
        path = separatorReplace (path);
        File folder = new File(path);
        if (folder.isDirectory ()) {
            return;
        } else if (folder.isFile ()) {
            deleteFile (path);
        }
        folder.mkdirs ();
    }

    /**
     * 创建一个新的文件夹，如果文件夹存在，则删除后再创建。
     * 
     * @param path
     * @throws Exception
     */
    public static void createNewFolder(String path) throws Exception {
        path = separatorReplace (path);
        File folder = new File(path);
        if (folder.isDirectory ()) {
            deleteFolder (path);
        } else if (folder.isFile ()) {
            deleteFile (path);
        }
        folder.mkdirs ();
    }

    /**
     * 创建一个文件，如果文件存在则不进行创建。
     * 
     * @param path
     * @throws Exception
     */
    public static File createFile(String path) throws Exception {
        path = separatorReplace (path);
        File file = new File(path);
        if (file.isFile ()) {
            return file;
        } else if (file.isDirectory ()) {
            deleteFolder (path);
        }
        return createFile (file);
    }

    /**
     * 创建一个新文件，如果存在同名的文件或文件夹将会删除该文件或文件夹， 如果父目录不存在则创建父目录。
     * 
     * @param path
     * @throws Exception
     */
    public static File createNewFile(String path) throws Exception {
        path = separatorReplace (path);
        File file = new File(path);
        if (file.isFile ()) {
            deleteFile (path);
        } else if (file.isDirectory ()) {
            deleteFolder (path);
        }
        return createFile (file);
    }

    /**
     * 分隔符替换 window下测试通过
     * 
     * @param path
     * @return
     */
    public static String separatorReplace(String path){
        return path.replace ("\\", "/");
    }

    /**
     * 创建文件及其父目录。
     * 
     * @param file
     * @throws Exception
     */
    public static File createFile(File file) throws Exception {
        createParentFolder (file);
        if (!file.createNewFile ()) { throw new Exception("create file (" + file.getPath () + ")failure!"); }
        return file;
    }

    /**
     * 创建父目录
     * 
     * @param file
     * @throws Exception
     */
    private static void createParentFolder(File file) throws Exception {
        if (!file.getParentFile ().exists ()) {
            if (!file.getParentFile ().mkdirs ()) { throw new Exception("create parent directory failure!"); }
        }
    }

    /**
     * 根据文件路径删除文件，如果路径指向的文件不存在或删除失败则抛出异常。
     * 
     * @param path
     * @return
     * @throws Exception
     */
    public static void deleteFile(String path){
        try {
            path = separatorReplace(path);
            File file = getFile(path);
            if (null != file && file.exists()) {
                file.delete();
            }
        }catch(Exception e){

        }
    }

    /**
     * 删除指定目录中指定前缀和后缀的文件。
     * 
     * @param dir
     * @param prefix
     * @param suffix
     * @throws Exception
     */
    public static void deleteFile(String dir, String prefix, String suffix) throws Exception {
        dir = separatorReplace (dir);
        File directory = getFolder (dir);
        if (null == directory) {
            return;
        }
        File[] files = directory.listFiles ();
        for ( File file : files ) {
            if (file.isFile ()) {
                String fileName = file.getName ();
                if (fileName.startsWith (prefix) && fileName.endsWith (suffix)) {
                    deleteFile (file.getAbsolutePath ());
                }
            }
        }
    }

    /**
     * 根据路径删除文件夹，如果路径指向的目录不存在则抛出异常， 若存在则先遍历删除子项目后再删除文件夹本身。
     * 
     * @param path
     * @throws Exception
     */
    public static void deleteFolder(String path) throws Exception {
        path = separatorReplace (path);
        File folder = getFolder (path);
        if (null == folder) {
            return;
        }
        File[] files = folder.listFiles ();
        for ( File file : files ) {
            if (file.isDirectory ()) {
                deleteFolder (file.getAbsolutePath ());
            } else if (file.isFile ()) {
                deleteFile (file.getAbsolutePath ());
            }
        }
        folder.delete ();
    }

    /**
     * 查找目标文件夹下的目标文件
     * 
     * @param dir
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static File searchFile(String dir, String fileName) throws FileNotFoundException {
        dir = separatorReplace (dir);
        File f = null;
        File folder = getFolder (dir);
        if (null == folder) {
            throw new FileNotFoundException(dir + " folder not found");
        }
        File[] files = folder.listFiles ();
        for ( File file : files ) {
            if (file.isDirectory ()) {
                f = searchFile (file.getAbsolutePath (), fileName);
                if (f != null) {
                    break;
                }
            } else if (file.isFile ()) {
                if (file.getName ().equals (fileName)) {
                    f = file;
                    break;
                }
            }
        }
        return f;
    }

    /**
     * 获得文件类型。
     * 
     * @param path
     *            ：文件路径
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileType(String path) throws FileNotFoundException {
        path = separatorReplace (path);
        File file = getFile (path);
        if (null == file) {
            throw new FileNotFoundException(path + " file not found");
        }
        String fileName = file.getName ();
        return  getFileTypeByFileName(fileName);
    }


    public static String getFileTypeByFileName(String fileName)  {

        String[] strs = fileName.split ("\\.");
        if (strs.length < 2) { return "unknownType"; }
        return strs[strs.length - 1];
    }


    /**
     * @Title: getFileName
     * @Description: 在文件路径中截取文件名
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileName(String filePath){
        filePath = separatorReplace (filePath);
        String fileName = filePath;
        if (fileName.indexOf ("/") > -1) {
            fileName = filePath.substring (filePath.lastIndexOf ("/") + 1, filePath.length ());
        }
        return fileName;
    }

    /**
     * 根据文件路径，获得该路径指向的文件的大小。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static long getFileSize(String path) throws FileNotFoundException {
        path = separatorReplace (path);
        File file = getFile (path);
        if (null == file) {
            throw new FileNotFoundException(path + " file not found");
        }
        return file.length ();
    }

    /**
     * @Title: formetFileSize
     * @Description: T转换文件大小
     * @param fileS
     * @return
     */
    public static String formatFileSize(long fileS){
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format ((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format ((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format ((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format ((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static double formatFileSize(long fileS,long length){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf (df.format ((double) fileS / length));
    }

    /**
     * 根据文件夹路径，获得该路径指向的文件夹的大小。 遍历该文件夹及其子目录的文件，将这些文件的大小进行累加，得出的就是文件夹的大小。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static long getFolderSize(String path) throws FileNotFoundException {
        path = separatorReplace (path);
        long size = 0;
        File folder = getFolder (path);
        if (null == folder) {
            throw new FileNotFoundException(path + " folder not found");
        }
        File[] files = folder.listFiles ();
        for ( File file : files ) {
            if (file.isDirectory ()) {
                size += getFolderSize (file.getAbsolutePath ());
            } else if (file.isFile ()) {
                size += file.length ();
            }
        }
        return size;
    }

    /**
     * 通过路径获得文件， 若不存在则抛异常， 若存在则返回该文件。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static File getFile(String path){
        path = separatorReplace (path);
        File file = new File(path);
        if (!file.isFile ()) {
            return null;
        }
        return file;
    }

    /**
     * 通过路径获得文件夹， 若不存在则抛异常， 若存在则返回该文件夹。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static File getFolder(String path){
        path = separatorReplace (path);
        File folder = new File(path);
        if (!folder.isDirectory ()) {
            return null;
        }
        return folder;
    }

    /**
     * 获得文件最后更改时间。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static Date getFileLastModified(String path) throws FileNotFoundException {
        path = separatorReplace (path);
        File file = getFile (path);
        if (null == file) {
            throw new FileNotFoundException(path + " file not found");
        }
        return new Date(file.lastModified ());
    }

    /**
     * 获得文件夹最后更改时间。
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static Date getFolderLastModified(String path) throws FileNotFoundException {
        path = separatorReplace (path);
        File folder = getFolder (path);
        if (null == folder) {
            throw new FileNotFoundException(path + " folder not found");
        }
        return new Date(folder.lastModified ());
    }

    /**
     * @Title: isExist
     * @Description: 判断文件夹是否存在)
     * @param path
     * @param create
     *            true 如果不存在创建，false不存在不创建
     * @return
     */
    public static boolean isExist(String path, boolean create){
        File file = new File(path);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists () && create) {
            file.setWritable (true);
            file.mkdirs ();
            return true;
        }
        return file.exists ();
    }

    /**
     * 将文件转成base64 字符串
     * 
     * @param path 文件路径
     * @return *
     * @throws Exception
     */

    public static String encodeBase64File(String path) throws Exception {
        return Base64.encodeFromFile (path);

    }

    /**
     * 将base64字符解码保存文件
     * 
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        Base64.decodeToFile (base64Code, targetPath);

    }

    /**
     * 将base64字符保存文本文件
     * 
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(String base64Code, String targetPath) throws Exception {

        byte[] buffer = base64Code.getBytes ();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write (buffer);
        out.close ();
    }

    /**
     * 将文件头转换成16进制字符串
     * 
     * @param src
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] src){

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) { return null; }
        for ( int i = 0 ; i < src.length ; i++ ) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString (v);
            if (hv.length () < 2) {
                stringBuilder.append (0);
            }
            stringBuilder.append (hv);
        }
        return stringBuilder.toString ();
    }

    /**
     * 得到文件头
     * 
     * @param filePath
     *            文件路径
     * @return 文件头
     * @throws IOException
     */
    private static String getFileContent(String filePath) throws IOException {

        byte[] b = new byte[4];
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStream.read (b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace ();
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        return bytesToHexString (b);
    }

    /**
     * 判断文件类型
     *
     * @param filePath
     *            文件路径
     * @return 文件类型
     */
    public static FileType getType(String filePath) throws IOException {
        String fileHead = getFileContent (filePath);
        if (fileHead == null || fileHead.length () == 0) { return null; }
        fileHead = fileHead.toUpperCase ();
        FileType[] fileTypes = FileType.values ();
        for ( FileType type : fileTypes ) {
            if (fileHead.startsWith (type.getValue ())) { return type; }
        }
        return null;
    }

    /**
     * @Title: getString4File
     * @Description: 读取文件内容
     * @param filePath
     * @param charset
     * @return
     */
    public static String getString4File(String filePath, String charset){
        StringBuffer sb = new StringBuffer();
        InputStream in = null;
        BufferedReader br = null;
        try {
        	File f = new File(filePath);
        	if(!f.exists()) {
        	    return null;
            }
            in = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(in,charset));
            String line = "";
            while ((line = br.readLine ()) != null) {
                sb.append (line);
            }
            return sb.toString ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            try {
                if (null != in) {
                    in.close ();
                }
                if (null != br) {
                    br.close ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return "";
    }

    /**
     * @Title: getString4File
     * @Description: 读取流文件
     * @Since: 2014年4月8日下午4:47:44
     * @param in
     * @param charset
     * @return
     */
    public static String getString4File(InputStream in, String charset){
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in,charset));
            String line = "";
            while ((line = br.readLine ()) != null) {
                sb.append (line);
            }
            return sb.toString ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            try {
                if (null != in) {
                    in.close ();
                }
                if (null != br) {
                    br.close ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return "";
    }

    /**
     * @Title: getFolderAllFile
     * @Description: 返回某个目录下所有文件
     * @Since: 2014年4月18日上午10:05:51
     * @param file
     * @return
     */
    public static List<File> getFolderAllFile(File file){
        List<File> list = new ArrayList<File>();
        if (file.isFile ()) {
            list.add (file);
        }
        else {
            File[] files = file.listFiles ();
            if (null != files && files.length > 0) {
                for ( File file2 : files ) {
                    if (file2.isDirectory ()) {
                        list.addAll (getFolderAllFile (file2));
                    }
                    else {
                        list.add (file2);
                    }
                }
            }
        }
        return list;
    }

    /**
     * @Title: getFolderAllFile
     * @Description: 返回某个目录下所有文件
     * @Since: 2014年4月18日上午10:08:25
     * @param folderPath
     * @return
     */
    public static List<File> getFolderAllFile(String folderPath){
        return getFolderAllFile (new File(folderPath));
    }

    /**
     * @Title: getLastFolderOrSuffix
     * @Description: 取路径最后文件夹或后缀
     * @param path
     * @return
     */
    public static String getLastFolderOrSuffix(String path){
        path = separatorReplace (path);
        return path.substring (path.lastIndexOf ("/") + 1, path.length ());

    }

    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return getFileMD5(in);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }


    public static String getFileMD5(InputStream in) {
        MessageDigest digest = null;
        byte buffer[] = new byte[10240];
        int len = 0;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer, 0, 10240)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }



    public static boolean downLoading(String resourcePath , String targetPath){
        try {
            java.net.URL url = new java.net.URL(resourcePath);
            final URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            FileOutputStream out = new FileOutputStream(new File(targetPath));
            int i = 0;
            while((i=is.read())!=-1){
                out.write(i);
            }

            out.close();
            is.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public static void main(String[] args){
        try {
            // String base64Code = encodeBase64File ("D:/0101-2011-qqqq.tif");
            // System.out.println (base64Code);
            // decoderBase64File (base64Code, "D:/2.tif");
            // toFile (base64Code, "D:\\three.txt");
            // System.out.println (getType ("‪F:/百度云/迅雷下载/ubuntu-12.04.2-dvd-amd64.iso"));

            downLoading("https://ubmcmm.baidustatic.com/media/v1/0f0002EGawuS9CaJEw2vF6.jpg","c:\\new.jpg");

            /*List<File> files = getFolderAllFile ("G:/project/beijing_iaas/PLITE/PD3/PD3/trunk/code/platform/pd3-collect/pd3-software-wrapper/src/main/webapp/softwares");
            for ( File file : files ) {
                System.out.println (file.getPath ());
            }*/
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}