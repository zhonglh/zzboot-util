package com.zzboot.util.config.enums;


/**
 * 验证码使用方式
 * @author admin
 */
public enum EnumCaptchaUserType {


    NEVER("0" , "从不使用验证码") ,
    ALWAYS("1" , "一直使用验证码") ,
    FAILURE("2" , "失败后使用验证码") ,
    ;


    String code;
    String name;

    EnumCaptchaUserType(String code, String name) {
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



    public EnumCaptchaUserType getEnum(String v){
        return EnumCaptchaUserType.getEnumByValue(v);
    }

    public static EnumCaptchaUserType getEnumByValue(String code){
        for(EnumCaptchaUserType e : EnumCaptchaUserType.values()){
            if(e.code.equals(code)){
                return e;
            }
        }
        return null;

    }

}
