package com.zzboot.util.remote;


import com.zzboot.util.base.java.EnumOsPlatform;

/**
 * @author zhonglh
 */
public class ExecCmd {
    
    public static String Runtime(String[] cmd){
        String os = System.getProperty ("os.name").toLowerCase ();
        if (
        		os.indexOf (EnumOsPlatform.Windows.toString().toLowerCase()) >= 0 ||
        				os.indexOf (EnumOsPlatform.Mac_OS.toString().toLowerCase()) >= 0||
        				os.indexOf (EnumOsPlatform.Mac_OS_X.toString().toLowerCase()) >= 0) {
            return ExecCmd4Win.Runtime (cmd);
        } else {
            return ExecCmd4Linux.Runtime (cmd);
        }

    }

    public static void validateErrorStream(String[] cmd){
        String os = System.getProperty ("os.name").toLowerCase ();
        if (
        		os.indexOf (EnumOsPlatform.Windows.toString().toLowerCase()) >= 0 ||
        				os.indexOf (EnumOsPlatform.Mac_OS.toString().toLowerCase()) >= 0||
        				os.indexOf (EnumOsPlatform.Mac_OS_X.toString().toLowerCase()) >= 0) {
            ExecCmd4Win.validateErrorStream (cmd);
        } else {
            ExecCmd4Linux.validateErrorStream (cmd);
        }
    }
}
