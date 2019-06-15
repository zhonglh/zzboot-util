package com.zzboot.util.template.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

/**
 * @author Administrator
 */
public class FreemarkerUtil {





    private static Configuration configuration = new Configuration();

    static {
        configuration.setNumberFormat("#");
    }


    /**
     * 根据模板生成文件
     * @param model 模板参数
     * @param dirPath 生成文件的路径
     * @param fileName 文件名称
     * @param ftlName 模板名称
     * @param ftlPath 模板路径
     * @throws IOException
     */
    public static void saveFile(Map<String, Object> model, String dirPath, String fileName, String ftlName, String ftlPath) throws IOException {

        Configuration cfg = buildConfiguration("classpath:" + ftlPath);
        Template template = cfg.getTemplate(ftlName);
        template.setEncoding("UTF-8");
        String result = renderTemplate(template, model);

        File dir = new File(dirPath);
        dir.mkdirs();
        String pathname = dirPath + File.separator + fileName;
        File file = new File(pathname);
        file.createNewFile();
        IOUtils.write(result, new FileOutputStream(file),"UTF-8");

    }

    /**
     * 渲染模板字符串。
     */
    public static String renderString(String templateString, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            Template t = new Template("default", new StringReader(templateString), configuration);
            t.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 渲染Template文件.
     */
    public static String renderTemplate(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建默认配置，设定模板目录.
     */
    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration();
        cfg.setNumberFormat("#");
        cfg.setDirectoryForTemplateLoading(new File(directory));
        return cfg;
    }
}
