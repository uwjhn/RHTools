package net.uwlau.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class KillNowHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// store config flags 
		boolean flag_kill_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill;
		boolean flag_scp_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp;;
		boolean flag_chmod_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod;;
		boolean flag_exec_tmp = net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec;;
		
		
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill = true;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod = false;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec = false;

		// run
		net.uwlau.plugin.rhtools.rt.Exec.rt_run();
		
		// restore config flags
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_kill = flag_kill_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_scp = flag_scp_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_chmod = flag_chmod_tmp;
		net.uwlau.plugin.rhtools.handlers.ConfigHandler.flag_exec = flag_exec_tmp;

		return null;
	}
}