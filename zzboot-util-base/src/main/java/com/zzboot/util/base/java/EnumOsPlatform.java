package com.zzboot.util.base.java;

public enum EnumOsPlatform {

    Windows("Windows"),  
    
    Linux("Linux"),  
    Mac_OS("Mac OS"),      
    Mac_OS_X("Mac OS X"),  
    OS2("OS/2"),  
    Solaris("Solaris"),  
    SunOS("SunOS"),  
    MPEiX("MPE/iX"),  
    HP_UX("HP-UX"),  
    AIX("AIX"),  
    CentOS("CentOS"),
    Ubuntu("Ubuntu"),
    OS390("OS/390"),  
    FreeBSD("FreeBSD"),  
    Irix("Irix"),  
    Digital_Unix("Digital Unix"),  
    NetWare_411("NetWare"),  
    OSF1("OSF1"),  
    OpenVMS("OpenVMS"),  
    Others("Others");  
    private EnumOsPlatform(String osName){
        this.osName = osName;  
    }  
      
    @Override
    public String toString(){
        return osName;  
    }  
      
    private String osName;
}
