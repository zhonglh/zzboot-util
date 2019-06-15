package com.zzboot.util.poi.enums;

/**
 * @author Administrator
 */

public enum EnumXlsFormat {


    DIGITS(3,"#,##0"),
    DATE(14,"yyyy/MM/dd"),
    NUMBER(2,"0.00"),
    DOUBLE(4,"##0.00"),
    CURRENCY(4,"#,##0.00"),
    PERCENT(11,"0.00%");

    private int code;
    private String pattern;

    EnumXlsFormat(int code, String pattern) {
        this.code = code;
        this.pattern = pattern;
    }


    public String getPattern() {
        return this.pattern;
    }

    public int getCode() {
        return code;
    }
}
