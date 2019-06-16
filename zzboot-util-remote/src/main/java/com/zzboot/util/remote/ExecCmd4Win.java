package com.zzboot.util.remote;


import lombok.extern.slf4j.Slf4j;
import net.neoremind.sshxcute.core.Result;

@Slf4j
public class ExecCmd4Win {


    /**
     * @param cmd
     *            传入 调用脚本的命令
     * @return 返回 流中的数据
     */
    public static String Runtime(String[] cmd){
        // 正确流
        try {
            String cmdrun = cmd[2];
                      
            Result r = RemoteShell.exec (cmdrun);
            String err = r.error_msg;
            if (null != err && !"".equals (err)) {
                // **错误流信息*//*
                log.debug ("errBuffer-----------" + err.toString ());
                throw new RuntimeException(err.toString ());
            }
            
            String stdin = r.sysout;
            StringBuffer stdinBuffer = null;
            if(stdin != null) {
                stdinBuffer = new StringBuffer(r.sysout);
            }

            if (stdinBuffer != null && stdinBuffer.toString ().length () > 0) { return stdinBuffer.toString (); }
        } catch (Exception e) {
            log.error (e.toString (), e);
            throw new RuntimeException(e.getMessage (),e);
        } finally {

        }
        return "";
    }

    /**
     * 该方法只验证错误流，如果有操作系统错误，就抛出
     * 
     * @param cmd
     */
    public static void validateErrorStream(String[] cmd){
        // 错误流
        try {
            String cmdrun = cmd[2];
            
            Result r = RemoteShell.exec (cmdrun);
            String errorline = r.error_msg;
            if (errorline != null && !errorline.toString ().equals ("")) { throw new RuntimeException(errorline.toString ()); }

        } catch (Exception e) {
        	log.error("", e);
            throw new RuntimeException(e.getMessage (),e);
        } finally {
        }

    }

}
