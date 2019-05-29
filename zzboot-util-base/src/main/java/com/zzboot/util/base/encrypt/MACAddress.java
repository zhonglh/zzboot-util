package com.zzboot.util.base.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 获取不同操作系统的mac地址
 */
public class MACAddress {

    private static final Logger logger = LoggerFactory.getLogger (MACAddress.class);

    public static List<String> getMacAddress(){
        List<String> macList = null;// 因存在多网卡问题 所以又多个MAC地址
        String address = null;
        String os = System.getProperty ("os.name");
        // logger.debug("Operation System: " + os);
        BufferedReader br = null;
        if (os.startsWith ("Windows")) {
            macList = new ArrayList<String>();
            try {
                String command = "cmd.exe /c ipconfig /all";
                Process p = Runtime.getRuntime ().exec (command);
                br = new BufferedReader(new InputStreamReader(p.getInputStream ()));
                String line;
                while ((line = br.readLine ()) != null) {
                    // logger.debug(line);
                    if (line.indexOf ("Physical Address") > 0 || line.indexOf ("物理地址") > 0) {
                        int index = line.indexOf (":");
                        index += 2;
                        address = line.substring (index);

                        address = address.trim ();
                        macList.add (address);
                    }
                }

            } catch (IOException e) {
                logger.error ("", e);
                throw new RuntimeException("获取windows MAC地址出错");
            } finally {
                try {
                    if (br != null) {
                        br.close ();
                    }
                } catch (IOException e) {
                    logger.error ("", e);
                }
            }
        } else if (os.startsWith ("Linux")) {
            macList = new ArrayList<String>();

            try {
                String command = "/bin/sh -c ifconfig -a";
                Process p = Runtime.getRuntime ().exec (command);
                br = new BufferedReader(new InputStreamReader(p.getInputStream ()));
                String line;
                while ((line = br.readLine ()) != null) {
                    if (line.indexOf ("HWaddr") > 0) {
                        int index = line.indexOf ("HWaddr") + "HWaddr".length ();
                        address = line.substring (index);
                        // LINUX下的mac地址分隔符为“:” 为适应系统统一处理 须转换成"-"
                        address = address.replaceAll (":", "-");
                        address = address.trim ();
                        macList.add (address);
                    }
                }
            } catch (IOException e) {
                logger.error ("", e);
                throw new RuntimeException("获取Linux MAC地址出错");
            } finally {
                try {
                    if (br != null) {
                        br.close ();
                    }
                } catch (IOException e) {
                    logger.error ("", e);
                }
            }
        } else if (os.startsWith ("AIX")) {
            BufferedReader entBr = null;

            BufferedReader macBr = null;
            macList = new ArrayList<String>();
            // 查找所有物理网卡的命令，命令行执行还依赖于用户，如何处理？
            String[] queryEntCommand = { "/usr/bin/ksh", "-c", "lsdev -Cc adapter|grep ent|grep Logical" };
            try {
                Process p = Runtime.getRuntime ().exec (queryEntCommand);
                // 命令行执行错误，说明不是在VIOS环境，虽然这样判断不标准
                InputStream err = p.getErrorStream ();
                if (err != null) {

                }
                entBr = new BufferedReader(new InputStreamReader(p.getInputStream ()));
                String devLine = null;
                while ((devLine = entBr.readLine ()) != null) {
                    String devName = devLine.substring (0, devLine.indexOf (" "));
                    // 查看网卡详细信息命令
                    String[] queryMacCommand = { "/usr/bin/ksh", "-c", "lscfg -vpl " };
                    queryMacCommand[2] = queryMacCommand[2] + devName;
                    Process pro = Runtime.getRuntime ().exec (queryMacCommand);
                    macBr = new BufferedReader(new InputStreamReader(pro.getInputStream ()));
                    String macLine = null;
                    while ((macLine = macBr.readLine ()) != null) {
                        if (macLine.indexOf ("Network Address") > 0) {
                            address = macLine.substring (macLine.lastIndexOf (".") + 1, macLine.length ());
                            macList.add (address);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                logger.error ("", e);
                throw new RuntimeException("获取AIX MAC地址出错");
            } finally {
                try {
                    if (entBr != null) {
                        entBr.close ();
                    }
                    if (macBr != null) {
                        macBr.close ();
                    }
                } catch (IOException e) {
                    logger.error ("", e);
                }
            }
        }
        return macList;
    }
}
