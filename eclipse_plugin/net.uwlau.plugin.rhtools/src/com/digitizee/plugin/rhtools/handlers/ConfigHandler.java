package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;


public class ConfigHandler extends AbstractHandler {
	
	public static String host = "";
	public static int port = 0;
	public static String user = "";
	public static String passwd = "";
	public static String binary_name = "";
	public static String path_to_binary = "";
	public static String custom_cmd = "";

	
	public static boolean flag_kill = true;
	public static boolean flag_scp = true;
	public static boolean flag_chmod = true;
	public static boolean flag_exec = true;
	public static boolean flag_shutdown = false; //always false in config
	public static boolean flag_reboot = false; //always false in config
	public static boolean flag_custom = false; //always false in config

	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// open window to get the custom command
		com.digitizee.plugin.rhtools.rt.Dialog_Config CFGdialog = new com.digitizee.plugin.rhtools.rt.Dialog_Config(null);
		CFGdialog.create();
		CFGdialog.open();
		
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.host=CFGdialog.getHost();
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.port=Integer.parseInt(CFGdialog.getPort());
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.user=CFGdialog.getUser();
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.passwd=CFGdialog.getPassword();
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.binary_name=CFGdialog.getBinaryName();
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.path_to_binary=CFGdialog.getPathToBinary();
		
		return null;
	}
}
