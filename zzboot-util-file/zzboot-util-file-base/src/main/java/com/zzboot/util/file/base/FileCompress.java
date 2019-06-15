package com.zzboot.util.file.base;


import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Work
 */

public class FileCompress {
    Log log = LogFactory.getLog( this .getClass());
    /**
     *  解压文件夹 zip
     * @param zipFile 压缩包路径
     * @param descDir 解压文件路径
     */
    public static void unZipFiles(String zipFile, String descDir) throws IOException {

        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(new File(zipFile) , Charset.forName("GBK"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            //输出文件路径信息
          //  System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
    }
    /**
     * 解压.tar.gz
     * @param dir 解压就文件路径
     * @param filepath  压缩包路径
     * @throws Exception
     */
    public static void targzArchive(String dir, String filepath)
            throws Exception {
        final File input = new File(filepath);
        final InputStream is = new FileInputStream(input);
        final CompressorInputStream in = new GzipCompressorInputStream(is, true);
        TarArchiveInputStream tin = new TarArchiveInputStream(in);
        TarArchiveEntry entry = tin.getNextTarEntry();
        while (entry != null) {
            File archiveEntry = new File(dir, entry.getName());
            archiveEntry.getParentFile().mkdirs();
            if (entry.isDirectory()) {
                archiveEntry.mkdir();
                entry = tin.getNextTarEntry();
                continue;
            }
            OutputStream out = new FileOutputStream(archiveEntry);
            IOUtils.copy(tin, out);
            out.close();
            entry = tin.getNextTarEntry();
        }
        in.close();
        tin.close();
    }
    /**
     * 解压tar包
     * @param filename
     *            tar文件
     * @param directory
     *            解压目录
     * @return
     */
    public static boolean extTarFileList(String filename, String directory) throws IOException {
        boolean flag = false;
        OutputStream out = null;
        try {
            TarInputStream in = new TarInputStream(new FileInputStream(new File(filename)));
            TarEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                //System.out.println(entry.getName());
                File outfile = new File(directory + entry.getName());
                new File(outfile.getParent()).mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(outfile));
                int x = 0;
                while ((x = in.read()) != -1) {
                    out.write(x);
                }
                out.close();
            }
            in.close();
            flag = true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            flag = false;
        }
        return flag;
    }
    /**
     * 根据原始rar路径，解压到指定文件夹下.
     * @param srcRarPath 原始rar路径
     * @param dstDirectoryPath 解压到的文件夹
     */
    public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
        if (!srcRarPath.toLowerCase().endsWith(".rar")) {
            return;
        }
        File dstDiretory = new File(dstDirectoryPath);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new File(srcRarPath));
            if (a != null) {
                //a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    //防止文件名中文乱码问题的处理
                    String fileName=fh.getFileNameW().trim();// = fh.getFileNameW().isEmpty() ? fh.getFileNameString() : fh.getFileNameW();
                    if(!existZH(fileName)){
                        fileName = fh.getFileNameString().trim();
                    }

                    if (fh.isDirectory()) { // 文件夹
                        File fol = new File(dstDirectoryPath + File.separator + fileName);
                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(dstDirectoryPath + File.separator + fileName.trim());
                        try {
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件名乱码处理
     * @param str 文件名
     * @return
     */
    private static boolean existZH(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            return true;
        }
        return false;
    }

}
