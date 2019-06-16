package com.zzboot.util.remote;


import lombok.extern.slf4j.Slf4j;
import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.impl.ExecCommand;



@Slf4j
public class RemoteShell {


 

    private static class SSHExecHolder {
        static SSHExec instance = getSSHExec ();
    }

    public static SSHExec getInstance(){
        if (SSHExecHolder.instance.isConnected ()) {
            return SSHExecHolder.instance;
        } else {
            SSHExecHolder.instance.disconnect ();
            SSHExecHolder.instance = getSSHExec ();
        }
        return SSHExecHolder.instance;
    }

    private static SSHExec getSSHExec(){
        ConnBean cb = new ConnBean (ShellConstant.SHELL_HOST,ShellConstant.SHELL_HOST_NAME,ShellConstant.SHELL_HOST_PASSWD);
        SSHExec ssh = new SSHExec (cb);
        ssh.connect ();
        return ssh;
    }

    public static Result exec(String cmd) throws TaskExecFailException{
    	SSHExec execer = RemoteShell.getInstance ();
    	log.info("---------------------------");
    	Result result = execer.exec (new ExecCommand (cmd));
        log.info("---------------------------");
    	return result;
    }
    
    

    public static void shutSSHService(){
    	SSHExecHolder.instance.disconnect ();
    }
    
    
    


    
    
    
    
    
    
    public static SSHExec getSSHExec(String ip, String user, String password){
    	ConnBean cb = new ConnBean(ip, user, password);
    	SSHExec ssh = new SSHExec(cb);
		ssh.connect();
		return ssh;
    }
    
    
    public static Result exec(SSHExec execer,String cmd) throws TaskExecFailException{
        log.info("---------------------------");
    	Result result = execer.exec (new ExecCommand (cmd));
        log.info("---------------------------");
    	return result;
    }
    
    

    public static void shutSSHService(SSHExec execer){
    	execer.disconnect ();
    }
    
    
}
