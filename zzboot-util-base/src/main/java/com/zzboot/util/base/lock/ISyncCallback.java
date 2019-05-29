package com.zzboot.util.base.lock;

public interface ISyncCallback {

    /**
     * @Title: isLocked
     * @Description: 当被锁时，执行此方法<br>
     *               此方法返回false，不执行run方法，<br>
     *               当方法返回true时等待锁执行run方法
     */
    boolean isLocked();

    /**
     * @Description: 同步执行方法主体
     */
    void run();
}
