package com.zzboot.util.file.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
public class FileViewer {

    public static List<String> fileList = new ArrayList<String>();

    /**
     * 读取文件夹下所有文件
     * 
     * @param dirPath
     * @param fileTypes
     * @return
     */
    public static List<String> getAllFilePathByDir_FilterByFileType(String dirPath, List<String> fileTypes){

        PathKit.getRootClassPath ();

        List<String> filePathList = new ArrayList<String>();

        fileList = new ArrayList<String>();
        List<String> arrayList = FileViewer.getListFiles (dirPath, fileTypes, true);
        if (arrayList.isEmpty ()) {} else {
            for (Iterator<String> i = arrayList.iterator (); i.hasNext () ; ) {
                String temp = (String) i.next ();
                // temp = temp.replace (contextFilePath, "");// 把根路径去掉
                filePathList.add (temp);
            }
        }

        // for (int replaceIndex = 0; replaceIndex < filePathList.size(); replaceIndex++) {
        // filePathList.set(replaceIndex, filePathList.get(replaceIndex).replace("\\", "/"));
        // }
        return filePathList;
    }

    /**
     * 添加文件后缀名称
     * 
     * @return
     */
    public static List<String> suffixlist(){
        List<String> list = new ArrayList<String>();
        list.add ("html");
        list.add ("htm");
        return list;
    }

    /**
     * @param path
     *            文件路径
     * @param suffix
     *            后缀名
     * @param isdepth
     *            是否遍历子目录
     * @return
     */
    private static List<String> getListFiles(String path, List<String> suffix, boolean isdepth){
        File file = new File(path);
        return FileViewer.listFile (file, suffix, isdepth);
    }

    private static List<String> listFile(File f, List<String> suffix, boolean isdepth){
        // 是目录，同时需要遍历子目录
        if (f.isDirectory () && isdepth == true) {
            if (f.getName ().indexOf (".") != -1) return fileList;

            File[] t = f.listFiles ();
            for ( int i = 0 ; i < t.length ; i++ ) {
                listFile (t[i], suffix, isdepth);
            }
        } else {
            String filePath = f.getAbsolutePath ();
            if (filePath.indexOf ("~$") != -1) return fileList;
            if (suffix != null) {
                int begIndex = filePath.lastIndexOf (".");// 最后一个.(即后缀名前面的.)的索引
                String tempsuffix = "";

                if (begIndex != -1)// 防止是文件但却没有后缀名结束的文件
                {
                    tempsuffix = filePath.substring (begIndex + 1, filePath.length ());
                }
                if (suffix.contains (tempsuffix)) {
                    fileList.add (filePath);
                }
            } else {
                // 后缀名为null则为所有文件
                fileList.add (filePath);
            }
        }
        return fileList;
    }

    public static void main(String[] args){

        /*
        List<String> listResult = getAllFilePathByDir_FilterByFileType(
        		"/fit/input",
        		suffixlist());
        System.out.println(listResult.size() + "\t" );*/
    }
}
