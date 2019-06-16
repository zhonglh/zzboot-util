package com.zzboot.util.poi.util;

import com.zzboot.util.base.data.StringUtil;
import com.zzboot.util.base.sorts.SortComparator;
import com.zzboot.util.config.Global;
import com.zzboot.util.config.annotaions.*;
import com.zzboot.util.config.util.AnnotaionEntityUtil;
import com.zzboot.util.poi.ExcelDictHolder;
import com.zzboot.util.poi.vo.Column;
import com.zzboot.util.spring.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

import javax.print.attribute.HashAttributeSet;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
public class ColumnUtil {

    private static Map<String,List<Column>> columnsMap = new ConcurrentHashMap<String,List<Column>>();
    private static Map<String,List<Field>> fieldsMap = new ConcurrentHashMap<String,List<Field>>();



    public static <T> List<Field> getBusinessAllFields(Class<T> clz){

        String key = clz.getName();
        List<Field> list = fieldsMap.get(key);
        if(list == null) {
            list = ReflectionUtil.getBusinessAllFields(clz , null);
            if(list != null) {
                fieldsMap.put(key, list);
            }
        }
        return list;
    }


    public static <T> List<Field> getBusinessAllFields(Class<T> clz, Class stopClz){

        String key = clz.getName()+stopClz.getName();
        List<Field> list = fieldsMap.get(key);
        if(list == null) {
            list = ReflectionUtil.getBusinessAllFields(clz, stopClz);
            if(list != null) {
                fieldsMap.put(key, list);
            }
        }
        return list;
    }


    public static <T> List<Column> getExcelColumn(Class<T> mclz , boolean isImport) {
        List<Column> columns = null;
        if(Global.getUserConfig().getExcelExportImpartSame()){
            columns = ColumnUtil.getAllExcelColumns(mclz);
        }else {
            columns = ColumnUtil.getAllExcelColumns(mclz, isImport);
        }
        return columns;
    }


    /**
     * 获取类型所有的Excel列， 包括导入或者导出类型的
     * @param clz       Class 类型
     * @param <T>
     * @return
     */
    private static  <T> List<Column> getAllExcelColumns(Class<T> clz) {

        String key = clz.getName();
        List<Column> list = columnsMap.get(key);
        if(list == null){
            list = getAllExcelColumns0(clz, true, false);
            if (list != null) {
                columnsMap.put(key, list);
            }
        }
        return list;
    }


    /**
     * 获取所有的Excel列
     * @param clz           类
     * @param isImport      是否导入
     * @param <T>
     * @return
     */
    public static  <T> List<Column> getAllExcelColumns(Class<T> clz, boolean isImport) {
        String key = clz.getName() + String.valueOf(isImport);
        List<Column> list = columnsMap.get(key);

        if(list == null){
            list = getAllExcelColumns0(clz, false, isImport);
            if(list != null) {
                columnsMap.put(key, list);
            }
        }
        return list;
    }
    private static  <T> List<Column> getAllExcelColumns0(Class<T> clz, boolean isAll, boolean isImport) {

        synchronized (clz) {

            List<Field> fs = ReflectionUtil.getExcelFields(clz, isAll, isImport);
            if (fs == null || fs.isEmpty()) {
                return null;
            }

            List<Column> list = new ArrayList<Column>(fs.size());
            for (Field f : fs) {
                Column column = field2Column(f);
                list.add(column);
            }

            Collections.sort(list, new SortComparator<Column>());
            int index = 0;
            for (Column column : list) {
                column.setNumber(index);
                index++;
            }


            return list;
        }

    }

    public static Column field2Column(Field f) {
        EntityAttrExcelAnnotation excelAnnotation = f.getAnnotation(EntityAttrExcelAnnotation.class);
        EntityAttrPageAnnotation pageAnnotation = f.getAnnotation(EntityAttrPageAnnotation.class);
        EntityAttrDBAnnotation dbAnnotation = f.getAnnotation(EntityAttrDBAnnotation.class);
        EntityAttrDictAnnotation dictAnnotation = f.getAnnotation(EntityAttrDictAnnotation.class);
        EntityAttrFkAnnotation fkAnnotation = f.getAnnotation(EntityAttrFkAnnotation.class);

        boolean required = AnnotaionEntityUtil.isRequired(dbAnnotation, fkAnnotation, dictAnnotation, pageAnnotation);
        int maxLength = AnnotaionEntityUtil.maxLength(dbAnnotation, fkAnnotation, dictAnnotation, pageAnnotation);

        Column column = new Column();
        column.setNumber(pageAnnotation.sort());
        column.setCode(f.getName());
        column.setLength(maxLength);
        column.setName(pageAnnotation.title());
        column.setRequired(required);
        if(excelAnnotation != null && excelAnnotation.width() != 0) {
            column.setWidth(excelAnnotation.width());
        }
        if(dictAnnotation != null && StringUtils.isNotEmpty(dictAnnotation.dictType()) && dictAnnotation.isNameField()){
            Map<String, List<String>> dictAllMap = ExcelDictHolder.getDictMap();
            if(dictAllMap != null){
                List<String> list = dictAllMap.get(dictAnnotation.dictType().toLowerCase());
                if(list != null && !list.isEmpty()){
                    if(!required){
                        list.add(0, "");
                    }
                    String[] combo = new String[list.size()];
                    combo = list.toArray(combo);
                    column.setCombo(combo);
                }
            }
        }
        column.setField(f);
        return column;
    }


    /**
     * 获的每个类中 所有的字典类型
     * @param fs
     * @return
     */
    public static String[] getDictTypeCodes(List<Field> fs) {
        Set<String> dictTypeSet = new HashSet<String>();
        for(Field f : fs){
            EntityAttrDictAnnotation dictAnnotation = f.getAnnotation(EntityAttrDictAnnotation.class);
            if(dictAnnotation != null){
                dictTypeSet.add(dictAnnotation.dictType());
            }
        }
        String[] arr = new String[dictTypeSet.size()];
        return dictTypeSet.toArray(arr);
    }


    /**
     * 获的所有的字典类型属性分组
     * 结果如 <"deptStatus",<deptStatus,deptStatusName> >
     * @param fs
     * @return
     */
    public static Map<String , Map<Field,Field>> getDictMap(List<Field> fs){

        Map<String,List<Field>> dictMap = new HashMap<String,List<Field>>();
        for(Field f : fs){
            EntityAttrDictAnnotation dictAnnotation = f.getAnnotation(EntityAttrDictAnnotation.class);
            if(dictAnnotation == null){
                continue;
            }
            List<Field> list = dictMap.get(dictAnnotation.group());
            if(list == null){
                list = new ArrayList<Field>();
                dictMap.put(dictAnnotation.group() , list);
            }
            list.add(f);
        }

        Map<String , Map<Field,Field>> result = new HashMap<String , Map<Field,Field>>();
        for(Map.Entry<String , List<Field>> dict : dictMap.entrySet()){
            String key = dict.getKey();
            List<Field> fields = dict.getValue();
            if(fields.size() != 2){
                throw new RuntimeException(key+"注解编写错误,个数为"+fields.size() + ", 应该为2");
            }

            EntityAttrDictAnnotation dictAnnotation1 = fields.get(0).getAnnotation(EntityAttrDictAnnotation.class);
            EntityAttrDictAnnotation dictAnnotation2 = fields.get(1).getAnnotation(EntityAttrDictAnnotation.class);
            Field dictVal = null;
            Field dictName = null;
            if(dictAnnotation1.isValueField()){
                dictVal = fields.get(0);
                if(dictAnnotation2.isNameField()){
                    dictName = fields.get(1);
                }
            }else {
                if(dictAnnotation1.isNameField()){
                    dictName = fields.get(0);
                }
                if(dictAnnotation2.isValueField()){
                    dictVal = fields.get(1);
                }
            }


            if(dictVal == null || dictName == null){
                throw new RuntimeException(key+"注解编写错误");
            }

            Map<Field,Field> map= new HashMap<Field,Field>();
            map.put(dictVal , dictName);
            result.put(key , map);
        }

        return result;
    }

    /**
     * 将所有的外键类型属性分组
     * 结果如 <"manageDeptId",<manageDeptId,<manageDeptId,manageDeptName,manageDeptCode,manageDeptLeadId> >
     * @param fs
     * @return
     */
    public static Map<String , Map<Field,List<Field>>> getFkMap(List<Field> fs){

        Map<String,List<Field>> fkMap = new HashMap<String,List<Field>>();
        for(Field f : fs){
            EntityAttrFkAnnotation fkAnnotation = f.getAnnotation(EntityAttrFkAnnotation.class);
            if(fkAnnotation == null){
                continue;
            }
            if(StringUtils.isEmpty(fkAnnotation.group())){
                continue;
            }
            List<Field> list = fkMap.get(fkAnnotation.group());
            if(list == null){
                list = new ArrayList<Field>();
                fkMap.put(fkAnnotation.group() , list);
            }
            list.add(f);
        }


        Map<String , Map<Field,List<Field>>> result = new HashMap<String , Map<Field,List<Field>>>();
        for(Map.Entry<String , List<Field>> dict : fkMap.entrySet()){

            String key = dict.getKey();
            List<Field> fields = dict.getValue();

            Field fkIdField = null;
            for(Field f : fields){
                EntityAttrFkAnnotation fkAnnotation = f.getAnnotation(EntityAttrFkAnnotation.class);
                if(fkAnnotation.isFkId()){
                    fkIdField = f;
                    break;
                }
            }
            if(fkIdField == null){
                throw new RuntimeException(key+"外键注解没有ID列");
            }

            Map<Field,List<Field>> map = new HashMap<Field,List<Field>>();
            map.put(fkIdField,fields);
            result.put(key , map);

        }
        return result;

    }



}
