package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

public class ConfigHandler extends AbstractHandler {

	public static String host = "172.22.166.135";
	public static int port = 22;
	public static String user = "pi";
	public static String passwd = "DAEbot";
	public static String binary_path = "/home/uwlau/DAEbot/Devices/Reflective Operator/Simple_C_Reflective_Operator/rPI_Cross/Simple_C_Reflective_Operator";
	public static String custom_cmd = "echo test";

	public static boolean flag_kill = true;
	public static boolean flag_scp = true;
	public static boolean flag_chmod = true;
	public static boolean flag_exec = false;
	
	public static boolean flag_kill_now = false;
	public static boolean flag_scp_now = false;
	public static boolean flag_exec_now = false;
	public static boolean flag_custom_now = false; 
	public static boolean flag_shutdown_now = false; 
	public static boolean flag_reboot_now = false; 


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// open window to get the custom command
		com.digitizee.plugin.rhtools.rt.Dialog_Config CFGdialog = new com.digitizee.plugin.rhtools.rt.Dialog_Config(
				null);
		CFGdialog.create();

		if (Window.OK == CFGdialog.open()) {

			com.digitizee.plugin.rhtools.handlers.ConfigHandler.host = CFGdialog.getHost();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.port = Integer.parseInt(CFGdialog.getPort());
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.user = CFGdialog.getUser();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.passwd = CFGdialog.getPassword();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_path = CFGdialog.getBinaryPath();

			com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill = CFGdialog.getKillFlag();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp = CFGdialog.getSCPFlag();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod = CFGdialog.getChmodFlag();
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec = CFGdialog.getExecFlag();
		}

		return null;
	}
}
