package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class CustomNowHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// store config flags
		boolean flag_kill_tmp = com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill;
		boolean flag_scp_tmp = com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp;;
		boolean flag_chmod_tmp = com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod;;
		boolean flag_exec_tmp = com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec;;

		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill = false;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp = false;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod = false;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec = false;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_custom = true;

		// open window to get the custom command
		com.digitizee.plugin.rhtools.rt.Dialog_CustomCMD dialog = new com.digitizee.plugin.rhtools.rt.Dialog_CustomCMD(null);
		dialog.create();
		dialog.open();
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.custom_cmd=dialog.getCMD();

		// run with custom command
		com.digitizee.plugin.rhtools.rt.Exec.rt_run();

		// restore config flags
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_kill = flag_kill_tmp;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_scp = flag_scp_tmp;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_chmod = flag_chmod_tmp;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec = flag_exec_tmp;
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_custom = false;

		return null;
	}

}