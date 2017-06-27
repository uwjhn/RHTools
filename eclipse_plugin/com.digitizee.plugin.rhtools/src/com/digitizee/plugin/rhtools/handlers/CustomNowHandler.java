package com.digitizee.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

public class CustomNowHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {


		com.digitizee.plugin.rhtools.handlers.ConfigHandler.flag_custom_now = true;

		// open window to get the custom command
		com.digitizee.plugin.rhtools.rt.Dialog_CustomCMD dialog = new com.digitizee.plugin.rhtools.rt.Dialog_CustomCMD(
				null);
		dialog.create();
		if (Window.OK == dialog.open()) {
			com.digitizee.plugin.rhtools.handlers.ConfigHandler.custom_cmd = dialog.getCMD();

			// run with custom command
			com.digitizee.plugin.rhtools.rt.Exec.rt_run();

		}

		return null;
	}

}