package com.zzboot.util.base.lock;

/**
 * 锁类型
 * @author Administrator
 */
public class LockType {

    private String type;

    public LockType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}
