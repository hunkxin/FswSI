package com.yike.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LinuxCMD {
	public static final String cmd_cpuidle = "vmstat | sed -n 3p | awk '{print $15}'";
	public static final String cmd_memfree = "free -h | grep \"^-/+ buffers/cache:\" | awk '{print $3$4}'";
	public static final String cmd_osrelease = "lsb_release -d";
	public static final String cmd_knrelease = "uname -v -m";
	public static final String cmd_tmnow = "date -R";
	public static final String cmd_devmsg = "df -h";
	public static final String cmd_rxtx = "cat /proc/net/dev | grep eth0 | awk '{print ($2/1024/1024),($10/1024/1024)}'";
	public static final String cmd_lasttm = "uptime | awk -F ' user' '{print $1}' | awk -F ' up ' '{print $2}' | awk -F ',' '{if(NF>2){print $1$2}else{print $1}}'";
	public static final String cmd_fs_status = "status";
	public static final String cmd_fs_channels = "show channels";
	public static final String cmd_fs_reg = "sofia status profile internal reg";
	public static final String cmd_fs_conf = "conference list";
	public static final String cmd_fs_reloadxml = "reloadxml";
	public static final String cmd_fs_shutdown = "shutdown";
	public static final String cmd_fs_conftime = "get run_time";
	public static final String cmd_os_dpt = "opensipsctl fifo ds_reload";
	public static final String cmd_os_ldblc = "opensipsctl fifo lb_reload";
	
	public static ArrayList<String> runCommand(boolean isfsw, String cmd) throws Exception{
		String tmp = (String) LinuxCMD.class.getField(cmd).get(null);
		if(isfsw){
			tmp = "/usr/local/freeswitch/bin/fs_cli -x "+"'"+tmp+"'"; 
		}
		String[] cmds = {"/bin/sh","-c",tmp}; 
		ArrayList<String> messages = new ArrayList<String>();
        Process pro = Runtime.getRuntime().exec(cmds);  
        pro.waitFor();  
        InputStream in = pro.getInputStream();  
        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        String line = null;  
        while((line = read.readLine())!=null){  
//            System.out.println(line);
            messages.add(line);
        }
        in.close();
        read.close();
        pro.destroy();
//      System.out.println(messages.size());
        return messages; 
	}
}
