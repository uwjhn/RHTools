package net.uwlau.plugin.rhtools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class AboutHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "RHTools",
				"RHTools  ©uwlau \nhttps://github.com/uwlau/RHTools");
				//"RHTools  ©uwlau \n<a href=\"https://github.com/uwlau/RHTools\">https://github.com/uwlau/RHTools</a>");

		return null;
	}
}