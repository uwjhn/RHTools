package net.uwlau.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class CustomNowHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// store config flags 
		boolean flag_kill_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill;
		boolean flag_scp_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp;;
		boolean flag_chmod_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod;;
		boolean flag_exec_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec;;
		
		
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_custom = true;
		
		// open window
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "RHTools",
				"Custom CMD todo");

		// run
		net.uwlau.plugin.rhtools.rt.Exec.rt_run();
		
		// restore config flags
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill = flag_kill_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp = flag_scp_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod = flag_chmod_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec = flag_exec_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_custom = false;

		return null;
	}
}