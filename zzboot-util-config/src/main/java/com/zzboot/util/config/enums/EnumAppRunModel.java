package com.zzboot.util.config.enums;

/**
 * 运行模式
 * @author Admin
 */

public enum EnumAppRunModel {


    DEV("dev" , "开发模式") ,
    TEST("test" , "测试模式") ,
    DEMO("demo" , "演示模式") ,
    PROD("prod" , "生产模式") ,
    ;


    String code;
    String name;

    EnumAppRunModel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getVal() {
        return code;
    }


    public String getLabel() {
        return name;
    }

    public String getValue() {
        return code;
    }



    public EnumAppRunModel getEnum(String v){
        return EnumAppRunModel.getEnumByValue(v);
    }

    public static EnumAppRunModel getEnumByValue(String code){
        for(EnumAppRunModel e : EnumAppRunModel.values()){
            if(e.code.equals(code)){
                return e;
            }
        }
        return null;

    }
    
}
