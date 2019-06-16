package com.zzboot.util.poi.enums;

/**
 * Excel 文件类型
 * @author Administrator
 */

public enum EnumExcelFileType {

    HSSF("hssf" , "xls"),
    XML("xml" , "xls"),
    SXSSF("sxssf" , "xlsx"),
    XSSF("xssf" , "xlsx"),
    CSV("csv" , "csv"),

    ;



    private String code;
    private String fileType;

    EnumExcelFileType(String code, String fileType) {
        this.code = code;
        this.fileType = fileType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }



    public static EnumExcelFileType getEnum(String code){
        if(code == null || code.isEmpty()){
            return null;
        }
        code = code.toLowerCase();
        for(EnumExcelFileType ft : EnumExcelFileType.values()){
            if(ft.getCode().equals(code)){
                return ft;
            }
        }
        return null;
    }


    public static EnumExcelFileType getEnumByFileType(String fileType){
        if(fileType == null || fileType.isEmpty()){
            return null;
        }
        fileType = fileType.toLowerCase();
        for(EnumExcelFileType ft : EnumExcelFileType.values()){
            if(ft.getFileType().equals(fileType)){
                return ft;
            }
        }

        return null;
    }




}
