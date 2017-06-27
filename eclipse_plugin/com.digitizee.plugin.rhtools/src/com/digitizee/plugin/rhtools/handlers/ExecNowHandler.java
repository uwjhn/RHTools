package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class ExecNowHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
				
		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_exec_now = true;
		
		// run
		com.digitizee.plugin.rhtools.rt.Exec.rt_run();

		return null;
	}
}