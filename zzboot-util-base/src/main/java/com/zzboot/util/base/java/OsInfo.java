package com.zzboot.util.base.java;



public class OsInfo {
	
	 	private static String OS = System.getProperty("os.name").toLowerCase();
     
	    private static OsInfo _instance = new OsInfo();  
	      
	    private EnumOsPlatform platform;
	      
	    private OsInfo(){}  
	      
	    public static boolean isLinux(){  
	        return OS.indexOf("linux")>=0;  
	    }  
	      
	    public static boolean isMacOS(){  
	        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;  
	    }  
	      
	    public static boolean isMacOSX(){  
	        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;  
	    }  
	      
	    public static boolean isWindows(){  
	        return OS.indexOf("windows")>=0;  
	    }  
	      
	    public static boolean isOS2(){  
	        return OS.indexOf("os/2")>=0;  
	    }  
	      
	    public static boolean isSolaris(){  
	        return OS.indexOf("solaris")>=0;  
	    }  
	      
	    public static boolean isSunOS(){  
	        return OS.indexOf("sunos")>=0;  
	    }  
	      
	    public static boolean isMPEiX(){  
	        return OS.indexOf("mpe/ix")>=0;  
	    }  
	      
	    public static boolean isHPUX(){  
	        return OS.indexOf("hp-ux")>=0;  
	    }  
	      
	    public static boolean isAix(){  
	        return OS.indexOf("aix")>=0;  
	    }  
	      
	    public static boolean isOS390(){  
	        return OS.indexOf("os/390")>=0;  
	    }  
	      
	    public static boolean isFreeBSD(){  
	        return OS.indexOf("freebsd")>=0;  
	    }  
	      
	    public static boolean isIrix(){  
	        return OS.indexOf("irix")>=0;  
	    }  
	      
	    public static boolean isDigitalUnix(){  
	        return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;  
	    }  
	      
	    public static boolean isNetWare(){  
	        return OS.indexOf("netware")>=0;  
	    }  
	      
	    public static boolean isOSF1(){  
	        return OS.indexOf("osf1")>=0;  
	    }  
	      
	    public static boolean isOpenVMS(){  
	        return OS.indexOf("openvms")>=0;  
	    }  
	    

	    public static boolean isCentOS(){  
	        return OS.indexOf("centos")>=0;  
	    }  
	    

	    public static boolean isUbuntu(){  
	        return OS.indexOf("ubuntu")>=0;  
	    }  
	      
	    /** 
	     * 获取操作系统名字 
	     * @return 操作系统名 
	     */  
	    public static EnumOsPlatform getOSname(){ 

	    	System.out.println("OS:"+OS);
	    	
	        if(isAix()){  
	            _instance.platform = EnumOsPlatform.AIX;  
	        }else if (isCentOS()) {  
	            _instance.platform = EnumOsPlatform.CentOS;  
	        }else if (isUbuntu()) {  
	            _instance.platform = EnumOsPlatform.Ubuntu;  
	        }else if (isDigitalUnix()) {  
	            _instance.platform = EnumOsPlatform.Digital_Unix;  
	        }else if (isFreeBSD()) {  
	            _instance.platform = EnumOsPlatform.FreeBSD;  
	        }else if (isHPUX()) {  
	            _instance.platform = EnumOsPlatform.HP_UX;  
	        }else if (isIrix()) {  
	            _instance.platform = EnumOsPlatform.Irix;  
	        }else if (isLinux()) {  
	            _instance.platform = EnumOsPlatform.Linux;  
	        }else if (isMacOS()) {  
	            _instance.platform = EnumOsPlatform.Mac_OS;  
	        }else if (isMacOSX()) {  
	            _instance.platform = EnumOsPlatform.Mac_OS_X;  
	        }else if (isMPEiX()) {  
	            _instance.platform = EnumOsPlatform.MPEiX;  
	        }else if (isNetWare()) {  
	            _instance.platform = EnumOsPlatform.NetWare_411;  
	        }else if (isOpenVMS()) {  
	            _instance.platform = EnumOsPlatform.OpenVMS;  
	        }else if (isOS2()) {  
	            _instance.platform = EnumOsPlatform.OS2;  
	        }else if (isOS390()) {  
	            _instance.platform = EnumOsPlatform.OS390;  
	        }else if (isOSF1()) {  
	            _instance.platform = EnumOsPlatform.OSF1;  
	        }else if (isSolaris()) {  
	            _instance.platform = EnumOsPlatform.Solaris;  
	        }else if (isSunOS()) {  
	            _instance.platform = EnumOsPlatform.SunOS;  
	        }else if (isWindows()) {  
	            _instance.platform = EnumOsPlatform.Windows;  
	        }else{  
	            _instance.platform = EnumOsPlatform.Others;  
	        }  
	        return _instance.platform;  
	    }  

}
