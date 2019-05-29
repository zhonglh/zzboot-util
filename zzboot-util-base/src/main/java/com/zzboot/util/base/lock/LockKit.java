package com.zzboot.util.base.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
public final class LockKit {

    private static class LockKitHolder {

        static LockKit instance = new LockKit ();
    }

    public static LockKit me(){
        return LockKitHolder.instance;
    }

    private LockKit() {}

    private Map<String, LockInfo> locks = new HashMap<String, LockInfo>();

    /**
     * getLock
     */
    public Lock getLock(LockType type, String id){
        return getLock (type, id, true);
    }

    private Lock l = new ReentrantLock();

    private Lock getLock(LockType type, String id, boolean isCount){
        l.lock ();
        try {
            String lockType = type.getType () + id;
            if (locks.containsKey (lockType)) {
                LockInfo lockinfo = locks.get (lockType);
                if (isCount) {
                    lockinfo.countjj ();
                }
                return lockinfo.getLock ();
            } else {
                LockInfo newLock = new LockInfo (new ReentrantLock(),lockType);
                if (isCount) {
                    newLock.countjj ();
                }
                locks.put (lockType, newLock);
                return newLock.getLock ();
            }
        } finally {
            l.unlock ();
        }

    }

    /**
     *  移除锁对像
     */
    public void remove(LockType type,String id){
        l.lock ();
        try {
            String lockType = type.getType () + id;
            ReentrantLock lock = null;
            if (locks.containsKey (lockType)) {
                LockInfo lockinfo = locks.get (lockType);
                lockinfo.countJJ ();
                lock = (ReentrantLock) lockinfo.getLock ();
                if (!lock.isLocked () && lockinfo.getCount () == 0) {
                    locks.remove (lockType);
                }
            }
        } finally {
            l.unlock ();
        }
    }

    public void lock(LockType type,String id){
        getLock (type, id, true).lock ();
    }

    /**
     * 同步执行
     */
    public void sync(LockType type, String id, ISyncCallback iCallback){
        ReentrantLock lock = (ReentrantLock) getLock (type, id, true);
        if (lock.isLocked ()) {
            if (iCallback.isLocked ()) {
                lock.lock ();
                try {
                    iCallback.run ();
                } catch (Exception e) {
                    throw e;
                } finally {
                    unlock (type, id);
                }
            } else {
                remove (type, id);
            }
        } else {
            lock.lock ();
            try {
                iCallback.run ();
            } catch (Exception e) {
                throw e;
            } finally {
                unlock (type, id);
            }
        }
    }

    public void unlock(LockType type,String id){
        getLock (type, id, false).unlock ();
        remove (type, id);
    }

    public static void main(String[] args) {
        LockKit.me().sync(new LockType("lock1") , "123" ,  new ISyncCallback(){

            @Override
            public boolean isLocked() {
                return true;
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"====" + System.currentTimeMillis());
            }
        });
    }

    /**
     * 锁信息
     */
    class LockInfo {

        /**
         * 锁对像
         */
        private Lock lock;


        /**
         * 锁被获取次数
         */
        private AtomicInteger count;


        /**
         * 锁类型
         */
        private String lockType;

        public LockInfo(Lock lock, String lockType) {
            super ();
            this.lock = lock;
            this.count = new AtomicInteger(0);
            this.lockType = lockType;
        }

        public Lock getLock(){
            return lock;
        }

        public int getCount(){
            return count.get ();
        }

        public int countjj(){
            return count.getAndIncrement ();
        }

        public int countJJ(){
            return count.getAndDecrement ();
        }

        public String getLockType(){
            return lockType;
        }

    }

}
