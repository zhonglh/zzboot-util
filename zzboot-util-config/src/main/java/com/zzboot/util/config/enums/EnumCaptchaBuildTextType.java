package com.zzboot.util.config.enums;


/**
 * 验证码使用方式
 * @author admin
 */
public enum EnumCaptchaBuildTextType {


    DEFAULT("0" , "默认方式") ,
    COMPUTE("1" , "计算方式") ,
    ;


    String code;
    String name;

    EnumCaptchaBuildTextType(String code, String name) {
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



    public EnumCaptchaBuildTextType getEnum(String v){
        return EnumCaptchaBuildTextType.getEnumByValue(v);
    }

    public static EnumCaptchaBuildTextType getEnumByValue(String code){
        for(EnumCaptchaBuildTextType e : EnumCaptchaBuildTextType.values()){
            if(e.code.equals(code)){
                return e;
            }
        }
        return null;

    }

}
