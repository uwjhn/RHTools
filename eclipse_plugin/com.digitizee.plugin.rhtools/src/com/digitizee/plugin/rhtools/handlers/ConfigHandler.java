package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

public class ConfigHandler extends AbstractHandler {

	public static String host = "192.168.1.121";
	public static int port = 50;
	public static String user = "pi";
	public static String passwd = "raspberry";
	public static String binary_path = "/default";
	public static String custom_cmd = "echo test";

	public static boolean flag_kill = false;
	public static boolean flag_scp = true;
	public static boolean flag_chmod = false;
	public static boolean flag_exec = true;
	public static boolean flag_shutdown = false; // always false in config
	public static boolean flag_reboot = false; // always false in config
	public static boolean flag_custom = false; // always false in config

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
